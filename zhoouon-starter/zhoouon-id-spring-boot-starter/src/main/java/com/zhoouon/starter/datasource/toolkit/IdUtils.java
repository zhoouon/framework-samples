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

package com.zhoouon.starter.datasource.toolkit;

import com.zhoouon.starter.datasource.core.DefaultIdGenerator;
import com.zhoouon.starter.datasource.core.IdGenerator;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zhoudong
 */
@UtilityClass
public final class IdUtils {

    private static final String BASE62_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static IdGenerator IDGENERATOR;

    public static final DateTimeFormatter MILLISECOND = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static void setIdGenerator(IdGenerator idGenerator) {
        IdUtils.IDGENERATOR = idGenerator;
    }

    private static final Long GEN = 1000000L;

    /**
     * 获取唯一ID
     * @return id
     */
    public long nextId() {
        if (IDGENERATOR == null) {
            IDGENERATOR = new DefaultIdGenerator();
        }
        return IDGENERATOR.nextId();
    }

    /**
     * 获取唯一ID
     * @return id
     */
    public String nextIdStr() {
        return String.valueOf(nextId());
    }

    /**
     * 基于基因拼接生成主键ID
     * @param serviceId 业务ID
     * @return
     */
    public String nextServiceId(Long serviceId) {
        long genId = serviceId % GEN;
        return nextIdStr() + genId;
    }

    /**
     * 获取固定格式的时间戳
     */
    public String getMillisecond() {
        return LocalDateTime.now().format(MILLISECOND);
    }

    /**
     * 获取随机ID
     * @return 随机ID用以替代传统的UUID
     */
    public String get32UUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return (new UUID(random.nextLong(), random.nextLong())).toString().replace("-", "");
    }

    /**
     * 生成62进制的短链ID
     * @return 短链ID
     */
    public String get62ShortID() {
        long num = nextId();
        StringBuilder sb = new StringBuilder();
        do {
            int remainder = (int) (num % 62);
            sb.insert(0, BASE62_CHARACTERS.charAt(remainder));
            num /= 62;
        } while (num != 0);
        return sb.toString();
    }

}
