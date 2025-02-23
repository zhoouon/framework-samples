package com.zhoouon.concurrent.volatiles;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhoudong
 * @Description: TODO 解决volatile在复合操作下无法保证原子性的问题，使用 AtomicInteger 保证原子性
 * @Date: 2024-06-16 17:29
 * @Version: 1.0.0
 **/
@Slf4j
public class volatileTest02 {
    public AtomicInteger incAtomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        useAtomicInteger();
    }

    public void increaseAtomicInteger() {
        incAtomicInteger.getAndDecrement();
    }

    /**
     * @param
     * @return: void
     * @description: 使用 AtomicInteger 保证原子性
     * @author: zhoudong
     * @date: 2024-06-16 17:42
     */
    public static void useAtomicInteger() {
        final volatileTest02 volatileTest = new volatileTest02();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        volatileTest.increaseAtomicInteger();
                    }
                }

                ;
            }.start();
        }
        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
            log.info("use AtomicInteger Thread Name is: {}, inc pitput: {}", Thread.currentThread().getName(), volatileTest.incAtomicInteger);
        }
    }
}
