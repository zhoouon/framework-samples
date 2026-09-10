package com.zhoouon.starter.datasource.core;

import com.zhoouon.starter.datasource.toolkit.IdUtils;

public interface IdGenerator {

    default String nextUUID() {
        return IdUtils.get32UUID();
    }

    long nextId();

}
