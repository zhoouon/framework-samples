package com.zhoouon.starter.web;

import com.zhoouon.starter.common.constant.CommonConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2024/3/18 10:59
 * 线程安全的请求头信息持有类
 */
public class RequestHeaderHolder {

    // 使用InheritableThreadLocal，使得共享变量可被子线程继承
    private final ThreadLocal<Map<String, String>> REQUEST_HEADER_HOLDER;

    private RequestHeaderHolder() {
        this.REQUEST_HEADER_HOLDER = new InheritableThreadLocal<>() {

            @Override
            protected Map<String, String> initialValue() {
                return new HashMap<>();
            }
        };
    }

    /**
     * return RequestHeaderHolder instance
     */
    public static RequestHeaderHolder getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 根据键获取请求头的值
     * @param key 请求头的键
     * @return 对应键的值
     */
    public String getValue(String key) {
        return this.REQUEST_HEADER_HOLDER.get().get(key);
    }

    /**
     * 获取当前线程的请求头信息
     * @return 请求头Map
     */
    public Map<String, String> get() {
        return this.REQUEST_HEADER_HOLDER.get();
    }

    /**
     * 获取当前线程的用户信息
     * @return 当前用户信息
     */
    public String getCurrentUser() {
        return this.REQUEST_HEADER_HOLDER.get().get(CommonConstant.X_CLIENT_TOKEN);
    }

    /**
     * 设置请求头信息
     */
    public void set(String key, String value) {
        this.REQUEST_HEADER_HOLDER.get().put(key, value);
    }

    public void clear() {
        this.REQUEST_HEADER_HOLDER.remove();
    }

    /**
     * 静态内部类的单例模式
     */
    private static class SingletonHolder {

        private static final RequestHeaderHolder instance = new RequestHeaderHolder();
        private SingletonHolder() {

        }
    }

}
