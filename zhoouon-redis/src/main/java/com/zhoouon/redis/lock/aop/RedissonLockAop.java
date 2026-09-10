package com.zhoouon.redis.lock.aop;

import com.alibaba.fastjson.JSONObject;
import com.zhoouon.redis.lock.annotation.RedissonLockAnnotation;
import com.zhoouon.redis.lock.utils.RedissonLockUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分布式锁的 aop
 */
@Aspect
@Component
@Slf4j
public class RedissonLockAop {

    /**
     * 切点，拦截被 @RedissonLockAnnotation 修饰的方法
     */
    @Pointcut("@annotation(com.zhoouon.redis.lock.annotation.RedissonLockAnnotation)")
    public void redissonLockPoint() {
    }

    @Around("redissonLockPoint()")
    public Object checkLock(ProceedingJoinPoint pjp) throws Throwable {
        // 当前线程名
        String threadName = Thread.currentThread().getName();
        log.info("线程{}------进入分布式锁aop------", threadName);

        // 获取该注解的实例对象
        RedissonLockAnnotation annotation = ((MethodSignature) pjp.getSignature()).
                getMethod().getAnnotation(RedissonLockAnnotation.class);
        // 生成分布式锁key的键名，以逗号分隔
        String lockRedisKey = annotation.lockRedisKey();
        if (StringUtils.isEmpty(lockRedisKey)) {
            log.warn("线程{} lockRedisKey设置为空，不加锁直接执行业务", threadName);
            return pjp.proceed();
        }

        JSONObject param = resolveFirstJsonParam(pjp.getArgs());
        // 生成分布式锁key，多个字段之间用 ":" 分隔，避免 "ab"+"c" 与 "a"+"bc" 碰撞
        String key = Stream.of(lockRedisKey.split(","))
                .map(String::trim)
                .filter(StringUtils::hasText)
                .map(field -> param.getString(field))
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.joining(":"));
        log.info("线程{} 锁的key={}", threadName, key);
        // 等待 3000ms，获取后持有 5000ms
        if (RedissonLockUtils.tryLock(key, 3000, 5000, TimeUnit.MILLISECONDS)) {
            try {
                log.info("线程{} 获取锁成功", threadName);
                return pjp.proceed();
            } finally {
                if (RedissonLockUtils.isLocked(key) && RedissonLockUtils.isHeldByCurrentThread(key)) {
                    RedissonLockUtils.unlock(key);
                    log.info("线程{} 释放锁", threadName);
                }
            }
        }
        log.info("线程{} 获取锁失败", threadName);
        return "GET LOCK FAIL";
    }

    private JSONObject resolveFirstJsonParam(Object[] args) {
        if (args == null || args.length == 0 || !(args[0] instanceof JSONObject param)) {
            throw new IllegalArgumentException("@RedissonLockAnnotation 仅支持第一个参数为 JSONObject 的方法");
        }
        return param;
    }
}
