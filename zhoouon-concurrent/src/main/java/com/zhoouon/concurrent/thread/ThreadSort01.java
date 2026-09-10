package com.zhoouon.concurrent.thread;

/**
 * @Author: zhoudong
 * @Description: TODO 测试线程的执行顺序
 * @Date: 2024/4/23 13:41
 * @Version: 1.0.0
 **/
public class ThreadSort01 {
    public static void main(String[] args) {
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
        thread02.start();
        thread03.start();
    }
}
