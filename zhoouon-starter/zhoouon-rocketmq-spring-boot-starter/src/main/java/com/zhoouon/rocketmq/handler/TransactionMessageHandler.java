package com.zhoouon.rocketmq.handler;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;

/**
 * 事务消息监听器，所有事务消息实现此接口而非RocketMQ的默认接口RocketMQLocalTransactionListener
 */
public interface TransactionMessageHandler {

    /**
    * 执行本地事务
    * @param payload 消息体
    * @param arg 参数
    */
    RocketMQLocalTransactionState executeLocalTransaction(Object payload, Object arg);

    /**
     * 检查本地执行状态
     * @param payload 消息体
     * @return 执行结果
     */
    RocketMQLocalTransactionState checkLocalTransaction(Object payload);

}
