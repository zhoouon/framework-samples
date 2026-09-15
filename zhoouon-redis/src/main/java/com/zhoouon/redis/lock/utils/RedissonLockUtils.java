package com.zhoouon.redis.lock.utils;


import com.zhoouon.redis.lock.lock.DistributeLocker;

import java.util.concurrent.TimeUnit;

/**
 * redisson锁工具类
 */
public class RedissonLockUtils {

    private static DistributeLocker locker;

    public static void setLocker(DistributeLocker locker) {
        RedissonLockUtils.locker = locker;
    }

    public static void lock(String lockKey) {
        locker.lock(lockKey);
    }

    public static void unlock(String lockKey) {
                locker.unlock(lockKey); }
    public static void lock(String lockKey, int timeout) {
        locker.lock(lockKey, timeout);
    }

    public static void lock(String lockKey, int timeout, TimeUnit unit) {
        locker.lock(lockKey, timeout, unit);
    }

    public static boolean tryLock(String lockKey) {
        return locker.tryLock(lockKey);
    }

    public static boolean tryLock(String lockKey, long waitTime, long leaseTime,
                                  TimeUnit unit) throws InterruptedException {
        return locker.tryLock(lockKey, waitTime, leaseTime, unit);
    }

    public static boolean isLocked(String lockKey) {
        return locker.isLocked(lockKey);
    }


    public static boolean isHeldByCurrentThread(String lockKey) {
        return locker.isHeldByCurrentThread(lockKey);
    }
}
