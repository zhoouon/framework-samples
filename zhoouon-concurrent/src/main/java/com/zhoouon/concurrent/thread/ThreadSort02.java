package com.zhoouon.concurrent.thread;

/**
 * @Author: zhoudong
 * @Description: TODO 让线程有序的执行 Thread.join()方法可以让线程有序的执行
 * @Date: 2024/4/23 13:44
 * @Version: 1.0.0
 **/
public class ThreadSort02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread01 = new Thread(()->{
            System.out.println("线程01");
        });

        Thread thread02 = new Thread(()->{
            System.out.println("线程02");
        });

        Thread thread03 = new Thread(()->{
            System.out.println("线程03");
        });

        thread01.start();
        thread01.join();

        thread02.start();
        thread02.join();

        thread03.start();
        thread03.join();
    }
}
