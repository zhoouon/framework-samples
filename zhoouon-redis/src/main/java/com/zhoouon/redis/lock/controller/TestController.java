package com.zhoouon.redis.lock.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhoouon.redis.lock.annotation.RedissonLockAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author : JCccc
 * @CreateTime : 2020/5/13
 * @Description :
 **/
@Slf4j
@RestController
public class TestController {

    @Autowired
    private RedissonClient redissonClient;

    @PostMapping(value = "testLock", consumes = "application/json")
    @RedissonLockAnnotation(lockRedisKey = "productName,platFormName")
    public String testLock(@RequestBody JSONObject params) {
        /**
         * 分布式锁key=params.getString("productName")+params.getString("platFormName");
         * productName 产品名称  platFormName 平台名称 如果都一致,那么分布式锁的key就会一直,那么就能避免并发问题
         */
        //TODO 业务处理

        try {
            System.out.println("接收到的参数：" + params.toString());
            System.out.println("执行相关业务...");
            System.out.println("执行相关业务.....");

            System.out.println("执行相关业务......");

        } catch (Exception e) {
            System.out.println("已进行日志记录");
        }

        return "success";
    }

    @GetMapping("/testData")
    public void testData() {

        // 插入 字符串
        RBucket<String> keyObj = redissonClient.getBucket("keyStr");
        keyObj.set("testStr", 300L, TimeUnit.SECONDS);

        //查询 字符串
        RBucket<String> keyGet = redissonClient.getBucket("keyStr");
        System.out.println(keyGet.get());

    }

    @GetMapping("/testList")
    public void testList() {

        // 插入 字符串
        RBucket<String> keyObj = redissonClient.getBucket("keyStr");
        keyObj.set("testStr", 300L, TimeUnit.SECONDS);

        //查询 字符串
        RBucket<String> keyGet = redissonClient.getBucket("keyStr");
        System.out.println(keyGet.get());

    }

    @GetMapping("/testMap")
    public void testMap() {

        // 插入 字符串
        RBucket<String> keyObj = redissonClient.getBucket("keyStr");
        keyObj.set("testStr", 300L, TimeUnit.SECONDS);

        //查询 字符串
        RBucket<String> keyGet = redissonClient.getBucket("keyStr");
        System.out.println(keyGet.get());

    }

    @GetMapping("/testSet")
    public void testSet() {

        // 插入 字符串
        RBucket<String> keyObj = redissonClient.getBucket("keyStr");
        keyObj.set("testStr", 300L, TimeUnit.SECONDS);

        //查询 字符串
        RBucket<String> keyGet = redissonClient.getBucket("keyStr");
        System.out.println(keyGet.get());

    }

    @GetMapping("/testLock/001")
    public String testStr() {
        RBucket<String> stockfair = redissonClient.getBucket("stockfair");
        log.info("从redis中查询的结果是: {}", stockfair.get());
        if (Objects.isNull(stockfair.get())) {
            redissonClient.getBucket("stockfair").set("100");
        }
        RLock lock = redissonClient.getFairLock("myFairLock");
        lock.lock();
        try {
            String stockFair = redissonClient.getBucket("stockfair").get().toString();
            if (StringUtils.isNotEmpty(stockFair)) {
                int stock = Integer.parseInt(stockFair);
                if (stock > 0) {
                    stock = stock - 1;
                    redissonClient.getBucket("stockfair").set(stock);
                    log.info("扣减成功，剩余库存：{}", stock);
                } else {
                    log.info("扣减失败，库存不足");
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            lock.unlock();
        }
        return "ok123";
    }

}
