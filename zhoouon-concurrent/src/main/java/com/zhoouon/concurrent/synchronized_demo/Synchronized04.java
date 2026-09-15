package com.zhoouon.concurrent.synchronized_demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhoudong
 * @Description: TODO 场景六 两个线程同时访问同一个对象的不同的同步方法
 *                    这个场景也是在探讨对象锁的作用范围，对象锁的作用范围是对象中的所有同步方法。所以，当访问同一个对象中的多个同步方法时，
 *                    结论是：「两个线程同时访问同一个对象的不同的同步方法时，是线程安全的。」
 * @Date: 2024-06-17 14:38
 * @Version: 1.0.0
 **/
@Slf4j
public class Synchronized04 implements Runnable{

     static Synchronized04 instance = new Synchronized04();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            //线程0,执行同步方法method0()
            method0();
        }
        if (Thread.currentThread().getName().equals("Thread-1")) {
            //线程1,执行同步方法method1()
            method1();
        }
    }

    private synchronized void method0() {
        log.info("线程名：{}，同步方法0，运行开始",Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程名：{}，同步方法0，运行结束",Thread.currentThread().getName());
    }

    private synchronized void method1() {
        log.info("线程名：{}，同步方法1，运行开始",Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程名：{}，同步方法1，运行结束",Thread.currentThread().getName());
    }

    //运行结果:串行
    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        System.out.println("测试结束");
    }

    /**
     * 结果分析：
     * 两个方法（method0()和method1()）的synchronized修饰符，虽没有指定锁对象，但默认锁对象为this对象为锁对象，
     * 所以对于同一个实例（instance），两个线程拿到的锁是同一把锁，此时同步方法会串行执行。这也是synchronized关键字的可重入性的一种体现。
     */
}
