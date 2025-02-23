package com.zhoouon.concurrent.future.taskcall;

/**
 * @Author: zhoudong
 * @Description: TODO 定义回调接口
 * @Date: 2024/4/23 14:01
 * @Version: 1.0.0
 **/
public interface TaskCallable<T> {

    T callable(T t);

}
