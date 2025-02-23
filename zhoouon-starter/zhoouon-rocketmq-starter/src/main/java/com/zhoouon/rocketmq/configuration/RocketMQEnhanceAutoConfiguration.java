package com.zhoouon.rocketmq.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zhoouon.rocketmq.handler.TransactionMessageHandler;
import com.zhoouon.rocketmq.listener.DefaultRocketMQTransactionListener;
import com.zhoouon.rocketmq.templete.RocketMQEnhanceTemplate;
import com.zhoouon.starter.common.spring.SpringBeanUtils;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(RocketEnhanceProperties.class)
public class RocketMQEnhanceAutoConfiguration {

    /**
     * 注入增强的RocketMQEnhanceTemplate
     */
    @Bean
    @ConditionalOnClass(RocketMQTemplate.class)
    public RocketMQEnhanceTemplate rocketMQEnhanceTemplate(RocketMQTemplate rocketMQTemplate, RocketEnhanceProperties rocketEnhanceProperties) {
        return new RocketMQEnhanceTemplate(rocketMQTemplate, rocketEnhanceProperties);
    }

    /**
     * 解决RocketMQ Jackson不支持Java时间类型配置
     * 源码参考：{@link org.apache.rocketmq.spring.autoconfigure.MessageConverterConfiguration}
     */
    @Bean
    @Primary
    public RocketMQMessageConverter enhanceRocketMQMessageConverter() {
        RocketMQMessageConverter converter = new RocketMQMessageConverter();
        CompositeMessageConverter compositeMessageConverter = (CompositeMessageConverter) converter.getMessageConverter();
        List<MessageConverter> messageConverterList = compositeMessageConverter.getConverters();
        for (MessageConverter messageConverter : messageConverterList) {
            if (messageConverter instanceof MappingJackson2MessageConverter jackson2MessageConverter) {
                ObjectMapper objectMapper = jackson2MessageConverter.getObjectMapper();
                objectMapper.registerModules(new JavaTimeModule());
                // 可以设置成base模块的ObjectMapper
                // jackson2MessageConverter.setObjectMapper();
            }
        }
        return converter;
    }

    @Bean
    @Primary
    public RocketMQLocalTransactionListener rocketMQLocalTransactionListener(final List<TransactionMessageHandler> transactionMessageHandlerList) {
        Map<String, TransactionMessageHandler> handlerMap = transactionMessageHandlerList.stream()
                .collect(Collectors.toMap(
                        handler -> handler.getClass().getSimpleName(),
                        handler -> SpringBeanUtils.getInstance().getBean(handler.getClass())));

        return new DefaultRocketMQTransactionListener(handlerMap);
    }

    /**
     * 环境隔离配置
     */
    @Bean
    @ConditionalOnProperty(name = "zhoouon.rocketmq.enabledIsolation", havingValue = "true")
    public EnvironmentIsolationConfig environmentSetup(RocketEnhanceProperties rocketEnhanceProperties) {
        return new EnvironmentIsolationConfig(rocketEnhanceProperties);
    }
}
