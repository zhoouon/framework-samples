package com.zhoouon.concurrent.volatiles;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhoudong
 * @Description: TODO 解决volatile在复合操作下无法保证原子性的问题，使用 synchronized 保证原子性
 * @Date: 2024-06-16 17:44
 * @Version: 1.0.0
 **/
@Slf4j
public class volatileTest03 {

    public int inc = 0;

    public static void main(String[] args) {
        useSynchronized();
    }

    public synchronized void increaseUseSynchronized() {
        inc++;
    }

    /**
     * @param
     * @return: void
     * @description: 使用 synchronized 保证原子性
     * @author: zhoudong
     * @date: 2024-06-16 17:33
     */
    public static void useSynchronized() {
        final volatileTest03 volatileTest = new volatileTest03();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        volatileTest.increaseUseSynchronized();
                    }
                };
            }.start();
        }
        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
            log.info("use synchronized Thread Name is: {}, inc pitput: {}", Thread.currentThread().getName(), volatileTest.inc);
        }
    }
}
