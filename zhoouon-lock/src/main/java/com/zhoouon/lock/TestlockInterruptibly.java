package com.zhoouon.lock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhoudong
 * @Description: TODO 中断响应：如果使用的是synchronized，要么获得锁，要么保持等待。而如果使用了重入锁，那就是线程可以被中断。
 *                    也就是在等待锁的过程中，程序可以根据需要取消对锁的请求。即：如果一个线程正在等待锁，那么它依然可以收到一个通知
 *                    被告知无需等待，可以停止工作了。可以很好的应对死锁的问题
 * @Date: 2024/4/15 15:53
 * @Version: 1.0.0
 **/
@Slf4j
public class TestlockInterruptibly {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    @SneakyThrows
    public static void main(String[] args) {
        // 入参相反，可以导致死锁
        Thread thread1 = new Thread(new ReentrantLockThread(lock1, lock2));
        Thread thread2 = new Thread(new ReentrantLockThread(lock2, lock1));
        thread1.start();
        thread2.start();
        // 主线程沉睡1秒，避免现成thread1直接响应run()方法中的睡眠中断
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("主线程开始沉睡第 1 秒");
        TimeUnit.MILLISECONDS.sleep(1000);
        log.info("主线程在 {}", thread1.getName(), "上开始执行 interrupt()方法");
        thread1.interrupt();
    }

    public static class ReentrantLockThread implements Runnable {
        private final ReentrantLock lock1;
        private final ReentrantLock lock2;

        public ReentrantLockThread(ReentrantLock lock1, ReentrantLock lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            try {
                // 对lock1进行加锁，获取lock1的可中断锁
                lock1.lockInterruptibly();
                log.info("加锁成功 1-2 : {}", Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(100);
                lock2.lockInterruptibly();
            } catch (InterruptedException e) {
                log.error("线程 {} 在等待锁时被中断", Thread.currentThread().getName(), e);
                Thread.currentThread().interrupt();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
                log.info(Thread.currentThread().getName() + "线程退出");
            }
        }
    }

}
