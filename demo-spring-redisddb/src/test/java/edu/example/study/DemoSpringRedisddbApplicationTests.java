package edu.example.study;

import edu.example.study.entity.User;
import edu.example.study.utill.RedisLock;
import edu.example.study.utill.RedissonLock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import java.util.concurrent.CyclicBarrier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringRedisddbApplicationTests {

	//@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//@Autowired
	private RedisTemplate<String,User> redisTemplate;
	//@Test
	public void contextLoads() {
		stringRedisTemplate.opsForValue().set("123", "321");
		Assert.assertEquals("321", stringRedisTemplate.opsForValue().get("123"));

		User user = new User(111,"abc");
		redisTemplate.opsForValue().set(user.getName(), user);

		user = new User(112,"vvc");
		redisTemplate.opsForValue().set(user.getName(), user);

		Assert.assertEquals(111,redisTemplate.opsForValue().get("abc").getId());
		Assert.assertEquals(112,redisTemplate.opsForValue().get("vvc").getId());
	}

	@Test
	public void testRedisLock() throws InterruptedException {
		try (Jedis jedis = new Jedis("172.16.38.159", 6379)) {
			boolean reuslt = RedisLock.tyrLock(jedis, "a", "1", 50000);
			if (reuslt)
				System.out.println("我获得锁：");

			Thread.sleep(2000);
			boolean a = RedisLock.releaseLock(jedis, "a", "1");
			System.out.println("释放了锁："+a);
		} catch (Exception e) {

		}
	}

	@Test
	public void testRedissonLock() throws InterruptedException {

		CyclicBarrier barrier = new CyclicBarrier(10);
		for (int i=0;i<10;i++) {
			Thread thread = new Thread(new Task(""+i,barrier));
			thread.start();
		}

		Thread.sleep(10000);

	}

	class Task implements Runnable {
		String id;
		CyclicBarrier barrier;
		public Task(String id,CyclicBarrier barrier) {
			this.id = id;
			this.barrier = barrier;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public CyclicBarrier getBarrier() {
			return barrier;
		}

		public void setBarrier(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			RedissonClient redisson = RedissonLock.getLock();
			try {
				getBarrier().await();
			} catch (Exception e) {
				e.printStackTrace();
			}

			RLock lock = redisson.getLock("lock");
			lock.lock();
			System.out.println(getId()+"获得锁，共有" + lock.getHoldCount() + "个线程使用锁");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {

				lock.unlock();
			}
		}
	}
}
