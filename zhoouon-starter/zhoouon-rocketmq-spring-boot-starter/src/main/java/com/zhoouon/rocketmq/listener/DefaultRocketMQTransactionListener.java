package com.zhoouon.rocketmq.listener;

import com.alibaba.fastjson.JSONObject;
import com.zhoouon.rocketmq.constant.MessageHeaderConstant;
import com.zhoouon.rocketmq.handler.TransactionMessageHandler;
import com.zhoouon.starter.common.toolkit.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.util.Map;

/**
 * Description: 分布式事务处理器,根据类型选择对应的实现类
 */
@Slf4j
@RocketMQTransactionListener
public class DefaultRocketMQTransactionListener implements RocketMQLocalTransactionListener {

    private final Map<String, TransactionMessageHandler> transactionMessageHandlerMap;

    public DefaultRocketMQTransactionListener(Map<String, TransactionMessageHandler> transactionMessageHandlerMap) {
        this.transactionMessageHandlerMap = transactionMessageHandlerMap;
    }

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        log.info("消费者收到事务消息[{}]", JSONObject.toJSON(message));
        String listenerName = (String) message.getHeaders().get(MessageHeaderConstant.TRANSACTION_MESSAGE_HEADER);

        if (null == listenerName) {
            throw new RuntimeException("not params transactionMessageListener");
        }

        RocketMQLocalTransactionState state;
        Object payload = message.getPayload();
        try {
            TransactionMessageHandler messageHandler = transactionMessageHandlerMap.get(listenerName);
            if (null == messageHandler) {
                throw new RuntimeException("not match condition TransactionMessageHandler");
            }
            state = messageHandler.executeLocalTransaction(payload, arg);
        } catch (Exception e) {
            log.error("rocket transaction message executeLocal error:{}", e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        }

        return state;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("消费者收到事务回查消息[{}]", JsonUtils.obj2String(message.getHeaders()));
        String listenerName = (String) message.getHeaders().get(MessageHeaderConstant.TRANSACTION_MESSAGE_HEADER);
        if (null == listenerName) {
            throw new RuntimeException("not params transactionMessageListener");
        }
        RocketMQLocalTransactionState state;
        try {
            TransactionMessageHandler messageHandler = transactionMessageHandlerMap.get(listenerName);
            if (null == messageHandler) {
                throw new RuntimeException("not match condition TransactionMessageHandler");
            }
            state = messageHandler.checkLocalTransaction(message.getPayload());
        } catch (Exception e) {
            log.error("rocket transaction message executeLocal error:{}", e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        }

        return state;
    }

}
