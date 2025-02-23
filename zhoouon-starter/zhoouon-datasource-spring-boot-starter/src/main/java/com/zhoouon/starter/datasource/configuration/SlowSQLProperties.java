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

package com.zhoouon.starter.datasource.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 * Redis 配置类
 */
@ConfigurationProperties(prefix = "zhoouon.sql.slow")
@Data
public class SlowSQLProperties {

    private boolean enable = false;

    private long cost = 2000L;

}
