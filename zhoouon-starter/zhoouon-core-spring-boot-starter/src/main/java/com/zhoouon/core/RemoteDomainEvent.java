/*
 * DailyMart is a microservice-based e-commerce project implemented
 * with Domain-Driven Design (DDD).
 * Copyright (C) 2023 Java日知录
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.zhoouon.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 基础消息模型
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/11/27 21:30
 */
@Data
@NoArgsConstructor
public class RemoteDomainEvent implements DomainEvent {

    /**
     * 业务键，用于RocketMQ控制台查看消费情况
     */
    protected String key;
    /**
     * 发送消息来源，用于排查问题
     */
    protected String source = "";

    /**
     * 发送时间
     */
    private final LocalDateTime sendTime = LocalDateTime.now();

    /**
     * 重试次数，用于判断重试次数，超过重试次数发送异常警告
     */
    protected Integer retryTimes = 0;

    public RemoteDomainEvent(String key, String source) {
        this.key = key;
        this.source = source;
    }

}
