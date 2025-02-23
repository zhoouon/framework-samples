package com.zhoouon.concurrent.future.taskcall;

/**
 * @Author: zhoudong
 * @Description: TODO 任务执行类
 * @Date: 2024/4/23 14:06
 * @Version: 1.0.0
 **/
public class TaskExecutor implements Runnable{

    private TaskCallable<TaskResult> taskCallable;
    private String taskParameter;

    public TaskExecutor(TaskCallable<TaskResult> taskCallable, String taskParameter) {
        this.taskCallable = taskCallable;
        this.taskParameter = taskParameter;
    }

    @Override
    public void run() {
        // TODO 一系列业务逻辑，将结果数据封装成TaskResult对象返回
        TaskResult taskResult = new TaskResult();
        taskResult.setTaskStatus(1);
        taskResult.setTaskMessage(this.taskParameter);
        taskResult.setTaskResult("异步回调成功");
        taskCallable.callable(taskResult);
    }
}
