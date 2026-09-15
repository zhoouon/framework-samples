package com.zhoouon.concurrent.future.result;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: zhoudong
 * @Description: TODO 测试FutureTask获取异步结果
 * @Date: 2024/4/23 14:27
 * @Version: 1.0.0
 **/
public class FutureTaskTest02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "测试FutureTask获取异步结果";
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
