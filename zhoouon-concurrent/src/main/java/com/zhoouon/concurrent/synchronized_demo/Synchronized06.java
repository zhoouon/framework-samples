package com.zhoouon.concurrent.synchronized_demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhoudong
 * @Description: TODO 场景八：同步方法抛出异常后，JVM会自动释放锁的情况
 *                     本场景探讨的是synchronized释放锁的场景：「只有当同步方法执行完或执行时抛出异常这两种情况，才会释放锁。」
 *                     所以，在一个线程的同步方法中出现异常的时候，会释放锁，另一个线程得到锁，继续执行。
 *                     而不会出现一个线程抛出异常后，另一个线程一直等待获取锁的情况。这是因为JVM在同步方法抛出异常的时候，会自动释放锁对象。
 * @Date: 2024-06-17 14:46
 * @Version: 1.0.0
 **/
@Slf4j
public class Synchronized06 implements Runnable {

    private static Synchronized06 instance = new Synchronized06();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            //线程0,执行抛异常方法method0()
            method0();
        }
        if (Thread.currentThread().getName().equals("Thread-1")) {
            //线程1,执行正常方法method1()
            method1();
        }
    }

    private synchronized void method0() {
        log.info("线程名：{}，运行开始",Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //同步方法中，当抛出异常时，JVM会自动释放锁，不需要手动释放，其他线程即可获取到该锁
        log.error("线程名：{}，抛出异常，释放锁",Thread.currentThread().getName());
        throw new RuntimeException();

    }

    private synchronized void method1() {
        log.info("线程名：{}，运行开始",Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程名：{}，运行结束",Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        log.info("测试结束");
    }

    /**
     * TODO 可以看出线程还是串行执行的，说明是线程安全的。而且出现异常后，不会造成死锁现象，JVM会自动释放出现异常线程的锁对象，其他线程获取锁继续执行。
     */
}
