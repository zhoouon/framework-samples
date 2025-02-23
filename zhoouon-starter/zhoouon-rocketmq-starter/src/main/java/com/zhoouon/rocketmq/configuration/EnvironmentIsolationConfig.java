package com.zhoouon.rocketmq.configuration;

import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.StringUtils;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/11/27 21:36
 */
public class EnvironmentIsolationConfig implements BeanPostProcessor {

    private final RocketEnhanceProperties rocketEnhanceProperties;

    public EnvironmentIsolationConfig(RocketEnhanceProperties rocketEnhanceProperties) {
        this.rocketEnhanceProperties = rocketEnhanceProperties;
    }

    /**
     * 在装载Bean之前实现参数修改
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, final String beanName) throws BeansException {
        if (bean instanceof DefaultRocketMQListenerContainer rocketMQListenerContainer) {

            if (rocketEnhanceProperties.isEnabledIsolation() && StringUtils.hasText(rocketEnhanceProperties.getEnvironment())) {
                rocketMQListenerContainer.setTopic(String.join("_", rocketMQListenerContainer.getTopic(), rocketEnhanceProperties.getEnvironment()));
            }
            return rocketMQListenerContainer;
        }
        return bean;
    }
}
