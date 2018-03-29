package edu.example.study;

import edu.example.study.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringRedisddbApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String,User> redisTemplate;
	@Test
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

}
