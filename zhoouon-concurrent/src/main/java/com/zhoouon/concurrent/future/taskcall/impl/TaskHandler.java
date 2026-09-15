package com.zhoouon.concurrent.future.taskcall.impl;

import com.zhoouon.concurrent.future.taskcall.TaskCallable;
import com.zhoouon.concurrent.future.taskcall.TaskResult;

/**
 * @Author: zhoudong
 * @Description: TODO 回调函数的实现类
 * @Date: 2024/4/23 14:04
 * @Version: 1.0.0
 **/
public class TaskHandler implements TaskCallable<TaskResult> {


    @Override
    public TaskResult callable(TaskResult taskResult) {
        // TODO 拿到结果数据之后进一步处理
        System.out.println(taskResult.toString());
        return taskResult;
    }
}
