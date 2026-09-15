package com.zhoouon.redis.lock.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024/3/18 12:54
 * @Version: 1.0.0
 **/
@Component
public class RedissonLock {

    @Autowired
    private RedissonClient redissonClient;

    public void getLock(){
        RLock lock = redissonClient.getLock("testLock");
        lock.lockAsync();
    }
}
