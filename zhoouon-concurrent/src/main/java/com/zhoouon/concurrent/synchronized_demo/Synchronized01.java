package com.zhoouon.concurrent.synchronized_demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhoudong
 * @Description: TODO synchronized 现成失效的场景
 *                  1.两个线程同时访问同一个对象的同步方法
 *                      分析：这种情况是经典的对象锁中的方法锁，两个线程争夺同一个对象锁，所以会相互等待，是线程安全的。
 *                  2.两个线程同时访问两个对象的同步方法
 *                      若要使锁生效，只需将method()方法用static修饰，这样就形成了类锁，多个实例（instance1、instance2）共同竞争一把类锁，就可以使两个线程串行执行了。这也就是下一个场景要讲的内容。
 *                  3.两个线程同时访问（一个或两个）对象的静态同步方法
 *                  4.两个线程分别同时访问（一个或两个）对象的同步方法和非同步方法
 *                  5.两个线程访问同一个对象中的同步方法，同步方法又调用一个非同步方法
 *                  6.两个线程同时访问同一个对象的不同的同步方法
 *                  7.两个线程分别同时访问静态synchronized和非静态synchronized方法
 *                  8.同步方法抛出异常后，JVM会自动释放锁的情况
 *                  https://blog.csdn.net/weixin_64051447/article/details/132564571
 * @Date: 2024-06-16 18:21
 * @Version: 1.0.0
 **/
@Slf4j
public class Synchronized01 implements Runnable {

    /**
     * 场景二：两个线程同时访问两个对象的同步方法
     * 这种场景就是对象锁失效的场景，原因出在访问的是两个对象的同步方法，那么这两个线程分别持有的两个线程的锁，所以是互相不会受限的。
     * 加锁的目的是为了让多个线程竞争同一把锁，而这种情况多个线程之间不再竞争同一把锁，而是分别持有一把锁，所以我们的结论是：
     * 「两个线程同时访问两个对象的同步方法，是线程不安全的。」
     */

    // 创建两个不同的对象
    static Synchronized01 instance1 = new Synchronized01();
    static Synchronized01 instance2 = new Synchronized01();

    @Override
    public void run() {
        method();
    }

    private synchronized void method(){
        log.info("Thread name is: {} ,运行开始", Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("thread error is: {}",e.getMessage());
        }
        log.info("Thread is: {} ,运行结束",Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {}
        log.info("Thread Test end!!!!!!!!");
    }

    /**
     * TODO 两个线程（thread1、thread2），访问两个对象（instance1、instance2）的同步方法（method()）,两个线程都有各自的锁，不能形成两个线程竞争一把锁的局势，
     *      所以这时，synchronized修饰的方法method()和不用synchronized修饰的效果一样（不信去把synchronized关键字去掉，运行结果一样），
     *      所以此时的method()只是个普通方法。
     *      「如何解决这个问题：」若要使锁生效，只需将method()方法用static修饰，这样就形成了类锁，多个实例（instance1、instance2）共同竞争一把类锁，就可以使两个线程串行执行了。
     */
}
