package com.zhoouon.starter.common.spring;

import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * SpringBeanUtils
 */
public final class SpringBeanUtils {

    private static final SpringBeanUtils INSTANCE = new SpringBeanUtils();

    private ApplicationContext applicationContext;

    private SpringBeanUtils() {
    }

    public static SpringBeanUtils getInstance() {
        return INSTANCE;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name bean name
     * @return bean
     */
    public Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param type class类型
     * @param  <T> 类型
     * @return bean
     */
    public <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name bean 名称
     * @param type 类型
     * @param  <T> 类型泛化
     * @return bean
     */
    public <T> T getBean(String name, Class<T> type) {
        return applicationContext.getBean(name, type);
    }

    /**
     * 通过class获取bean map对象
     * @param type 类型
     */
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * set application context.
     *
     * @param applicationContext application context
     */
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
