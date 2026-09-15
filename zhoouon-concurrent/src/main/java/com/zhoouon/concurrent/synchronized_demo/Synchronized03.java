package com.zhoouon.concurrent.synchronized_demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhoudong
 * @Description: TODO 场景五：两个线程访问同一个对象中的同步方法，同步方法又调用一个非同步方法
 * @Date: 2024-06-16 18:32
 * @Version: 1.0.0
 **/
@Slf4j
public class Synchronized03 implements Runnable {

    static Synchronized03 instance = new Synchronized03();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            //直接调用普通方法
            method2();
        } else {
            // 先调用同步方法，在同步方法内调用普通方法
            method1();
        }
    }

    // 同步方法
    private static synchronized void method1() {
        log.info("线程名：{}，同步方法，运行开始", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程名：{}，同步方法，运行结束,开始调用普通方法", Thread.currentThread().getName());
        method2();
    }

    // 普通方法
    private static void method2() {
        log.info("线程名：{}，普通方法，运行开始", Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程名：{}，普通方法，运行结束", Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // 此线程直接调用普通方法
        Thread thread0 = new Thread(instance);
        // 这两个线程直接调用同步方法
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread0.start();
        thread1.start();
        thread2.start();
        while (thread0.isAlive() || thread1.isAlive() || thread2.isAlive()) {
        }
        log.info("测试结束");
    }

    /**
     * TODO 我们可以看出，普通方法被两个线程并行执行，不是线程安全的。这是为什么呢？
     *      因为如果非同步方法，有任何其他线程直接调用，而不是仅在调用同步方法时，才调用非同步方法，此时会出现多个线程并行执行非同步方法的情况，线程就不安全了。
     *      对于同步方法中调用非同步方法时，要想保证线程安全，就必须保证非同步方法的入口，仅出现在同步方法中。
     *      但这种控制方式不够优雅，若被不明情况的人直接调用非同步方法，就会导致原有的线程同步不再安全。
     *      所以不推荐大家在项目中这样使用，但我们要理解这种情况，并且我们要用语义明确的、让人一看就知道这是同步方法的方式，来处理线程安全的问题。
     *      所以，最简单的方式，是在非同步方法上，也加上synchronized关键字，使其变成一个同步方法，
     *      这样就变成了《场景五：两个线程同时访问同一个对象的不同的同步方法》，这种场景下，大家就很清楚的看到，同一个对象中的两个同步方法，不管哪个线程调用，都是线程安全的了。
     *      所以结论是：「两个线程访问同一个对象中的同步方法，同步方法又调用一个非同步方法，仅在没有其他线程直接调用非同步方法的情况下，是线程安全的。若有其他线程直接调用非同步方法，则是线程不安全的。」
     */

}
