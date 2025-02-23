package com.zhoouon.concurrent.volatiles;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhoudong
 * @Description: TODO volatile禁止重排序不使用的场景 volatile不适合复合操作
 * @Date: 2024-06-16 16:41
 * @Version: 1.0.0
 **/
@Slf4j
public class volatileTest01 {

    /**
     * TODO 期望输出是 10000，但是由于volatile在复合场景下，所以会出现结果小于10000的情况
     *      因为 inc++ 不是一个原子操作，可以由读取、加、赋值三步组成，所以结果并不能达到10000
     */
    public volatile int inc = 0;

    public void increase(){
        inc++;
    }

    public static void main(String[] args) {
        final volatileTest01 volatileTest01 = new volatileTest01();
        for (int i = 0; i < 10; i++) {
            new Thread(){
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        volatileTest01.increase();
                    }
                };
            }.start();
        }
        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
            //System.out.println("inc pitput:" + volatileTest01.inc);
            log.info("Thread Name is: {}, inc pitput: {}",Thread.currentThread().getName(),volatileTest01.inc);
        }
    }
}
