package com.zhoouon.concurrent.synchronized_demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: zhoudong
 * @Description: TODO 场景四 两个线程分别同时访问（一个或两个）对象的同步方法和非同步方法 (线程不安全)
 * @Date: 2024-06-16 18:32
 * @Version: 1.0.0
 **/
@Slf4j
public class Synchronized02 implements Runnable {

    static Synchronized02 instance = new Synchronized02();

    @Override
    public void run() {
        if (StringUtils.equals(Thread.currentThread().getName(), "Thread-0")) {
            // 线程0，执行同步方法 method0
            method0();
        }
        if (StringUtils.equals(Thread.currentThread().getName(), "Thread-1")) {
            // 线程1，执行同步方法 method1
            method1();
        }
    }

    private synchronized void method0(){
        log.info("线程名: {},同步方法，运行开始", Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程名: {},同步方法，运行结束", Thread.currentThread().getName());
    }

    // 普通方法
    private synchronized void method1() {
        log.info("线程名: {},普通方法，运行开始", Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程名: {},普通方法，运行结束", Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {}
        log.info("测试结束");
    }

    /**
     * TODO 问题在于此：method1没有被synchronized修饰，所以不会受到锁的影响。即便是在同一个对象中，当然在多个实例中，更不会被锁影响了。
     *      结论：「非同步方法不受其它由synchronized修饰的同步方法影响」
     */
}
