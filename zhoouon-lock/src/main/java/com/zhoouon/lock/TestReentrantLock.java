package com.zhoouon.lock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhoudong
 * @Description: 重入锁：就是允许一个线程可以反复进入，
 * @Date: 2024/4/15 15:37
 * @Version: 1.0.0
 **/
@Slf4j
public class TestReentrantLock {
    public static int num;

    @SneakyThrows
    public static void main(String[] args) {

        CountTask task = new CountTask();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log.info("num: {}", num);
    }

    private static class CountTask implements Runnable {

        private final ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            log.info(" start: [{}]",Thread.currentThread().getName());
            for (int i = 0; i < 10000000; i++) {
                lock.lock();
                try {
                    num++;
                }finally {
                    lock.unlock();
                }
            }
            log.info(" end: [{}]",Thread.currentThread().getName());
        }
    }
}
