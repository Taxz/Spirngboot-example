##### 数据库实现

```
	一是基于数据库表，另一种是基于数据库排他锁。
	如应用主从数据库，数据之间双向同步。一旦挂掉快速切换到备库上；做定时任务，每隔一定时间把数据库中的超时数据清理一遍；
	优点：直接借助数据库，简单容易理解。
	缺点：操作数据库需要一定的开销，性能问题需要考虑。
```
##### 基于ZooKeeper的分布式锁

```
	zookeeper临时有序节点可以实现的分布式锁。每个客户端对某个方法加锁时，在zookeeper上的与该方法对应的指定节点的目录下，生成一个唯一的瞬时有序节点。 判断是否获取锁的方式很简单，只需要判断有序节点中序号最小的一个。 当释放锁的时候，只需将这个瞬时节点删除即可。同时，其可以避免服务宕机导致的锁无法释放，而产生的死锁问题。
	缺点：性能上可能并没有缓存服务那么高。因为每次在创建锁和释放锁的过程中，都要动态创建、销毁瞬时节点来实现锁功能。ZK中创建和删除节点只能通过Leader服务器来执行，然后将数据同步到所有的Follower机器上。并发问题，可能存在网络抖动，客户端和ZK集群的session连接断了，zk集群以为客户端挂了，就会删除临时节点，这时候其他客户端就可以获取到分布式锁了。
```

##### 基于Redis的分布式锁。

```
四个条件：
    互斥性。在任意时刻，只有一个客户端能持有锁。
    不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
    容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
    解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了
    
    加锁使用 jedis.set(String key, String value, String nxxx, String expx, int time)方法；
    key，我们使用key来当锁，因为key是唯一的。
   value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，分布式锁要满足第四个条件解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。requestId可以使用UUID.randomUUID().toString()方法生成。
    nxxx，这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
    expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
    time，与第四个参数相呼应，代表key的过期时间。
   
   释放锁：采用lua脚本，判断id是否相同，相同则是否锁，避免是否了不是自己的锁
   String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";        
   Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(requestId));
```

##### Redisson

​	引用官网的简介，Redisson内部提供了一个监控锁的看门狗，它的作用是在Redisson实例被关闭前，不断的延长锁的有效期。默认情况下，看门狗的检查锁的超时时间是30秒钟，也可以通过修改[Config.lockWatchdogTimeout](https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95#lockwatchdogtimeout%E7%9B%91%E6%8E%A7%E9%94%81%E7%9A%84%E7%9C%8B%E9%97%A8%E7%8B%97%E8%B6%85%E6%97%B6%E5%8D%95%E4%BD%8D%E6%AF%AB%E7%A7%92)来另行指定。 

```
写demo的时候出现错误：
	java.lang.ClassNotFoundException: io.netty.channel.epoll.EpollEventLoopGroup.....
添加依赖：
		<dependency>
			<groupId>org.redisson</groupId>
			<artifactId>redisson</artifactId>
			<version>3.10.0</version>
		</dependency>
```
