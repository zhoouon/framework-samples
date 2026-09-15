package com.zhoouon.concurrent.volatiles;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhoudong
 * @Description: TODO 解决volatile在复合操作下无法保证原子性的问题，使用 lock 保证原子性
 * @Date: 2024-06-16 17:47
 * @Version: 1.0.0
 **/
@Slf4j
public class volatileTest04 {

    public int inc = 0;
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        useLock();
    }

    public void increaseUseLock() {
        try {
            lock.lock();
            inc++;
        } catch (Exception e) {
            log.error("increaseUseLock error {}", e);
        }finally {
            lock.unlock();
        }

    }

    /**
     * @param
     * @return: void
     * @description: 使用 lock 保证 inc 原子性
     * @author: zhoudong
     * @date: 2024-06-16 17:35
     */
    private static void useLock() {
        final volatileTest04 volatileTest = new volatileTest04();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        volatileTest.increaseUseLock();
                    }
                };
            }.start();
        }
        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
            log.info("use lock Thread Name is: {}, inc pitput: {}", Thread.currentThread().getName(), volatileTest.inc);
        }
    }
}
