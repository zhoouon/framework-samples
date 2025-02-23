package com.zhoouon.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024/4/24 14:20
 * @Version: 1.0.0
 **/
@Slf4j
public class CreateThreadPool01 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 20,
                100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(400));
        threadPoolExecutor.execute(() -> {
            System.out.println("hello");
            try {
                Thread.sleep(1000);
                System.out.println("world");
            } catch (InterruptedException e) {

            }
        });
        log.info("线程池中核心线程数目：" + threadPoolExecutor.getCorePoolSize() + "，线程池中最大线程数目是：" + threadPoolExecutor.getPoolSize() + "，已执行玩的任务数目：" + threadPoolExecutor.getCompletedTaskCount());
        threadPoolExecutor.shutdown();
    }

}
