package edu.example.study.utill;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * Created by Taxz on 2019/1/15.
 */
public class RedisLock {

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_EXPIRE_TIME = "PX";
    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_LOCK = 1L;

    /**
     *
     * @param jedis redis客户端
     * @param key 锁键
     * @param requestId 值，请求id,通过判断释放锁，避免其他误释放
     * @param expireTime 锁过期时间
     * @return
     */
    public static boolean tyrLock(Jedis jedis, String key, String requestId, int expireTime) {
        String result = jedis.set(key, requestId, SET_IF_NOT_EXIST, SET_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param jedis 客户端
     * @param key 锁键
     * @param requestId 请求id，通过判断id,来释放锁
     * @return
     */
    public static boolean releaseLock(Jedis jedis, String key, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(requestId));

        if (RELEASE_LOCK.equals(result)) {
            return true;
        }
        return false;
    }
}
