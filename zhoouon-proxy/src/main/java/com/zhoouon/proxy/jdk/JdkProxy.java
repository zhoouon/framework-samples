package com.zhoouon.proxy.jdk;

import com.zhoouon.proxy.jdk.service.FoodService;
import com.zhoouon.proxy.jdk.service.impl.FoodServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024/3/24 16:23
 * @Version: 1.0.0
 **/
@Slf4j
public class JdkProxy implements InvocationHandler {

    private Object target;

    public JdkProxy(Object target) {
        super();
        this.target = target;
    }

    public Object createProxy(){
        // 1.创建目标对象的类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 2.获取目标对象的实现接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 3.第三个参数需要一个实现了InvocationHandler接口的对象
        Object newProxyInstance = Proxy.newProxyInstance(classLoader, interfaces, this);
        return newProxyInstance;
    }

    // 第一个参数:代理对象.一般不使用;第二个参数:需要增强的方法;第三个参数:方法中的参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("这是jdk动态代理增强方法之前");
        Object invoke = method.invoke(target, args);
        log.info("这是jdk动态代理增强方法之后");
        return invoke;
    }

    public static void main(String[] args) {
        // 1.创建对象
        FoodServiceImpl foodService = new FoodServiceImpl();
        // 2.创建代理对象
        JdkProxy jdkProxy = new JdkProxy(foodService);
        // 3.调用代理对象的增强方法，得到增强后的对象
        FoodService proxy = (FoodService) jdkProxy.createProxy();
        proxy.makeChicken();
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    }
}
