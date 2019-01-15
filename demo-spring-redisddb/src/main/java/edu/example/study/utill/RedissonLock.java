package edu.example.study.utill;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * Created by Taxz on 2019/1/15.
 */
public class RedissonLock {

    //创建redisson实例
    public static RedissonClient getLock() {
        //创建配置
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        //指定使用单节点部署方式
        config.useSingleServer().setAddress("redis://172.16.38.159:6379");

       return Redisson.create(config);
    }
}
