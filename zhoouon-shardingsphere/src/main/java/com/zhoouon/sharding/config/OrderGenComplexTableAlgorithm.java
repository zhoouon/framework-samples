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

package com.zhoouon.sharding.config;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;

/**
 * 基于基因拼接算法的复合分片算法,基于主键ID
 */
public class OrderGenComplexTableAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {

    private static final String SHARDING_COUNT_KEY = "sharding-count";

    private static final String ID = "id";
    private static final String REPAY_NO_COLUMN = "repay_no";

    private int shardingCount;

    @Getter
    private Properties props;

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> shardingValue) {

        Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();

        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());

        if (MapUtils.isNotEmpty(columnNameAndShardingValuesMap)) {
            // 获取ID
            Collection<Comparable<?>> idCollection = columnNameAndShardingValuesMap.get(ID);
            // ID分片
            if (CollectionUtils.isNotEmpty(idCollection)) {
                idCollection.stream().findFirst().ifPresent(comparable -> {
                    long tableNameSuffix = (Long) comparable % shardingCount;
                    result.add(shardingValue.getLogicTableName() + "_" + tableNameSuffix);
                });
            }
            //else {
            //    Collection<Comparable<?>> orderSnCollection = columnNameAndShardingValuesMap.get(REPAY_NO_COLUMN);
            //    orderSnCollection.stream().findFirst().ifPresent(comparable -> {
            //        String repayNo = String.valueOf(comparable);
            //        // 获取ID基因
            //        String substring = repayNo.substring(Math.max(0, repayNo.length() - 6));
            //        long tableNameSuffix = Long.parseLong(substring) % shardingCount;
            //        result.add(shardingValue.getLogicTableName() + "_" + tableNameSuffix);
            //    });
            //}
        }
        return result;

    }

    @Override
    public void init(Properties props) {
        this.props = props;
        shardingCount = getShardingCount(props);
    }

    private int getShardingCount(Properties props) {
        Preconditions.checkArgument(props.containsKey(SHARDING_COUNT_KEY), "Sharding count cannot be null.");
        return Integer.parseInt(props.getProperty(SHARDING_COUNT_KEY));
    }
}
