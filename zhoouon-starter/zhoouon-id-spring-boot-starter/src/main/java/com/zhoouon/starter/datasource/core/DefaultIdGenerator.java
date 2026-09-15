package com.zhoouon.starter.datasource.core;

import com.zhoouon.starter.datasource.toolkit.IdUtils;

/**
 * 基于Seata构建优化后的雪花算法包装
 */
public class DefaultIdGenerator implements IdGenerator {

    private final IdWorker idWorker;

    public  DefaultIdGenerator() {
        // 动态生成workId，也可以直接使用new IdWorker(null) 作为构造函数。
        // 建议使用动态生成，不然在K8S部署的时候 workId 会重复
        // long workId = new Random().nextLong() & 1023;
        // log.info("构建ID生成器时使用随机workId，它的值为: {}", workId);
        this.idWorker = new IdWorker(null);
        // 设置IdUtils
        IdUtils.setIdGenerator(this);
    }

    @Override
    public long nextId() {
        return idWorker.nextId();
    }
}
