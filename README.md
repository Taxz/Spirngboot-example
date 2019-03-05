# Spirngboot-example

##### [demo-activemq](https://github.com/Taxz/Spirngboot-example/tree/master/demo-activemq)

​	springboot 集成activemq集群demo

##### [demo-ftp](https://github.com/Taxz/Spirngboot-example/tree/master/demo-ftp)

​	ftp使用demo，注意：在使用ftp连接池会出现获取连接异常的情况，

##### [demo-jpa-redis-autocache](https://github.com/Taxz/Spirngboot-example/tree/master/demo-jpa-redis-autocache) 

​	jpa+mysql+redis缓存

##### [demo-spring-redisddb](https://github.com/Taxz/Spirngboot-example/tree/master/demo-spring-redisddb) 

​	分析了几种分布式锁的实现方式的优劣，及redisson分布式锁的使用，[说明文档](https://github.com/Taxz/Spirngboot-example/blob/master/demo-spring-redisddb/READE.md)。

##### [demo-springboot-redis](https://github.com/Taxz/Spirngboot-example/tree/master/demo-springboot-redis) 

​	通过mybatis拦截器实现分页

##### [sharding-jdbc](https://github.com/Taxz/Spirngboot-example/tree/master/sharding-jdbc) 

​	使用当当的sharding-jdbc实现分库分表

Zookeeper master 选举

​	Curator工具类

​	LeaderLatch:LeaderLatch则一直持有leadership， 除非调用close方法，否则它不会释放领导权。 

​	LeaderSelector:封装了所有和master选举相关的逻辑，包括所有和zookeeper服务器交互的过程，LeaderSelectorListener可以对领导权进行控制，在执行完takeLeadership()释放控制权，这样每个节点都有可能获得领导权。 