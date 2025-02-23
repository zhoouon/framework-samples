package com.zhoouon.starter.common.exception;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024-07-09 11:59
 * @Version: 1.0.0
 **/
public final class ExceptionCodeCache implements BeanFactoryPostProcessor {

    private static final String EXCEPTION_RESOURCE_CONFIG_PATH = "/META-INF/exception-config.properties";
    private static final String EXCEPTION_RESOURCE_CONFIG_KEY = "exception.message.source.name";
    private static String componentName = "common";
    private static Map<String, String> cache = new ConcurrentHashMap();

    public ExceptionCodeCache() {
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.inject();
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void inject() {
        try {
            String configPath = this.getExceptionMessageResourceConfigValue();
            Resource resource = new ClassPathResource(configPath);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            this.putCache(properties);
        } catch (IOException var4) {
            IOException e = var4;
            e.printStackTrace();
            throw new SamplesApplicationException("系统启动失败, 初始化配置异常配置异常");
        }
    }

    private void putCache(Properties properties) {
        Iterator it = properties.keySet().iterator();

        while(it.hasNext()) {
            String key = (String)it.next();
            String value = properties.getProperty(key);
            this.put(key, value);
        }

        this.put("99999999", "系统异常，请联系管理员");
    }

    private String getExceptionMessageResourceConfigValue() throws IOException {
        Resource resource = new ClassPathResource("/META-INF/exception-config.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        String fileName = properties.getProperty("exception.message.source.name");
        return "/META-INF/" + this.componentName + "/messages/" + fileName;
    }

    private void put(String key, String value) {
        cache.put(key, value);
    }

    public static String get(String key) {
        return (String)cache.get(key);
    }

    public static String get(String key, Object... args) {
        String message = (String)cache.get(key);
        if (ArrayUtil.isNotEmpty(args)) {
            message = MessageFormat.format(message, args);
        }

        return message;
    }
}
