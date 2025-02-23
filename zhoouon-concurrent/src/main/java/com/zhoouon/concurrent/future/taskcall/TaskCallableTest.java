package com.zhoouon.concurrent.future.taskcall;

import com.zhoouon.concurrent.future.taskcall.impl.TaskHandler;

/**
 * @Author: zhoudong
 * @Description: TODO 测试回调
 * @Date: 2024/4/23 14:10
 * @Version: 1.0.0
 **/
public class TaskCallableTest {
    public static void main(String[] args) {
        TaskCallable<TaskResult> taskHandler = new TaskHandler();
        TaskExecutor taskExecutor = new TaskExecutor(taskHandler, "异步回调");
        new Thread(taskExecutor).start();
    }
}
