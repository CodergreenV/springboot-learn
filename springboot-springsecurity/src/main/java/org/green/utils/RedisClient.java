package org.green.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Component
public class RedisClient {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 保存数据
     * @param key key
     * @param value value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存数据-过期时间
     * @param key key
     * @param value value
     * @param timeout 过期时间
     */
    public void set(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 通过键获取对应的值
     * @param key key
     * @return    value
     */
    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
    /**
     * 通过键删除对应的值
     * @param key key
     */
    public void del(String key){
        stringRedisTemplate.delete(key);
    }
    /**
     * 判断key是否存在
     * @param key key
     */
    public Boolean exists(String key){
        return stringRedisTemplate.hasKey(key);
    }
}
