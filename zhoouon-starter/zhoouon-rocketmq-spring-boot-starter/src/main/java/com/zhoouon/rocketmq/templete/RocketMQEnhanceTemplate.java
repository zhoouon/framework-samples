package com.zhoouon.rocketmq.templete;

import com.alibaba.fastjson.JSONObject;
import com.zhoouon.core.RemoteDomainEvent;
import com.zhoouon.rocketmq.configuration.RocketEnhanceProperties;
import com.zhoouon.rocketmq.handler.TransactionMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.StringUtils;

import static com.zhoouon.rocketmq.constant.MessageHeaderConstant.SOURCE_HEADER;
import static com.zhoouon.rocketmq.constant.MessageHeaderConstant.TRANSACTION_MESSAGE_HEADER;

/**
 * RocketMQ增强工具类
 */
@Slf4j
public class RocketMQEnhanceTemplate {

    private final RocketMQTemplate rocketMQTemplate;
    private final RocketEnhanceProperties rocketEnhanceProperties;

    public RocketMQEnhanceTemplate(RocketMQTemplate rocketMQTemplate, RocketEnhanceProperties rocketEnhanceProperties) {
        this.rocketMQTemplate = rocketMQTemplate;
        this.rocketEnhanceProperties = rocketEnhanceProperties;
    }

    /**
     * 根据系统上下文自动构建隔离后的topic
     * 构建目的地
     */
    public String buildDestination(String topic, String tag) {
        topic = reBuildTopic(topic);
        return topic + ":" + tag;
    }

    /**
     * 根据环境重新隔离topic
     * @param topic 原始topic
     */
    private String reBuildTopic(String topic) {
        if (rocketEnhanceProperties.isEnabledIsolation() && StringUtils.hasText(rocketEnhanceProperties.getEnvironment())) {
            return topic + "_" + rocketEnhanceProperties.getEnvironment();
        }
        return topic;
    }

    /**
     * 发送同步消息
     */
    public <T extends RemoteDomainEvent> SendResult send(String topic, String tag, T message) {
        // 注意分隔符
        return send(buildDestination(topic, tag), message);
    }

    public <T extends RemoteDomainEvent> SendResult send(String destination, T message) {
        // 设置业务键，此处根据公共的参数进行处理
        // 更多的其它基础业务处理...
        Message<T> sendMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, message.getKey()).build();
        SendResult sendResult = rocketMQTemplate.syncSend(destination, sendMessage);
        // 此处为了方便查看给日志转了json，根据选择选择日志记录方式，例如ELK采集
        log.info("[{}]同步消息[{}]发送结果[{}]", destination, JSONObject.toJSON(message), JSONObject.toJSON(sendResult));
        return sendResult;
    }

    /**
     * 发送延迟消息
     */
    public <T extends RemoteDomainEvent> SendResult sendDelay(String topic, String tag, T message, int delayLevel) {
        return send(buildDestination(topic, tag), message, delayLevel);
    }

    /**
     * 发送事务消息
     */
    public <T extends RemoteDomainEvent> TransactionSendResult sendTransaction(String topic, String tag, T message, Class<? extends TransactionMessageHandler> transactionMessageListener) {
        if (transactionMessageListener == null) {
            throw new IllegalArgumentException("transactionMessageListener must not null");
        }

        String destination = buildDestination(topic, tag);

        Message<T> sendMessage = MessageBuilder.withPayload(message)
                .setHeader(RocketMQHeaders.KEYS, message.getKey())
                .setHeader(SOURCE_HEADER, message.getSource())
                .setHeader(TRANSACTION_MESSAGE_HEADER, transactionMessageListener.getSimpleName())
                .build();

        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, sendMessage, null);

        log.info("[{}]事务消息[{}]发送结果[{}]", destination, JSONObject.toJSON(message), JSONObject.toJSON(sendResult));

        return sendResult;
    }

    public <T extends RemoteDomainEvent> SendResult send(String destination, T message, int delayLevel) {
        Message<T> sendMessage = MessageBuilder.withPayload(message)
                .setHeader(RocketMQHeaders.KEYS, message.getKey())
                .setHeader(SOURCE_HEADER, message.getSource())
                .build();
        SendResult sendResult = rocketMQTemplate.syncSend(destination, sendMessage, 3000, delayLevel);
        log.info("[{}]延迟等级[{}]消息[{}]发送结果[{}]", destination, delayLevel, JSONObject.toJSON(message), JSONObject.toJSON(sendResult));
        return sendResult;
    }
}
