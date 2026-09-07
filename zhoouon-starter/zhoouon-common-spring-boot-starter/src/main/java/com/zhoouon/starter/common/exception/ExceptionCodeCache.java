package com.zhoouon.starter.common.exception;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.beans.BeansException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.text.MessageFormat;
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

    private static final Logger logger = LoggerFactory.getLogger(ExceptionCodeCache.class);
    private static final String EXCEPTION_RESOURCE_CONFIG_PATH = "classpath*:/META-INF/exception-config.properties";
    private static final String MESSAGE_RESOURCE_PATH_PATTERN = "classpath*:/META-INF/common/messages/{0}";
    private static final Map<String, String> cache = new ConcurrentHashMap<>();

    public ExceptionCodeCache() {
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.inject();
    }

    public void inject() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] configResources = resolver.getResources(EXCEPTION_RESOURCE_CONFIG_PATH);
            if (configResources.length == 0) {
                throw new SamplesApplicationException("系统启动失败, 未找到异常码配置文件: " + EXCEPTION_RESOURCE_CONFIG_PATH);
            }
            for (Resource configResource : configResources) {
                Properties config = PropertiesLoaderUtils.loadProperties(configResource);
                String messageSource = config.getProperty("exception.message.source.name");
                if (messageSource == null || messageSource.isBlank()) {
                    continue;
                }
                Resource[] messageResources = resolver.getResources(
                        MessageFormat.format(MESSAGE_RESOURCE_PATH_PATTERN, messageSource));
                for (Resource messageResource : messageResources) {
                    this.putCache(PropertiesLoaderUtils.loadProperties(messageResource));
                }
            }
        } catch (IOException e) {
            logger.error("初始化异常码缓存失败", e);
            throw new SamplesApplicationException("系统启动失败, 初始化配置异常配置异常");
        }
    }

    private void putCache(Properties properties) {
        properties.forEach((key, value) -> cache.put(String.valueOf(key), String.valueOf(value)));
        cache.put("99999999", "系统异常，请联系管理员");
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
