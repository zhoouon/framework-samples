package com.zhoouon.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoudong
 * @Description: TODO 线程无限等待
 * @Date: 2024/4/23 13:36
 * @Version: 1.0.0
 **/
public class ThreadWait implements Runnable {

    @Override
    public void run() {
        while (true) {
            waitSecond(200);
        }
    }

    private static void waitSecond(long i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ThreadWait threadWait = new ThreadWait();
        Thread thread = new Thread(threadWait);
        thread.start();
    }
}
