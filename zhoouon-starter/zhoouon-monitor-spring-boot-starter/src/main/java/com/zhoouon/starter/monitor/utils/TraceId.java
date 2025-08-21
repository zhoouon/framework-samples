package com.zhoouon.starter.monitor.utils;

import org.slf4j.MDC;

import java.util.UUID;

public final class TraceId {
    public static final String KEY = "traceId";
    public static String ensure() {
        String tid = MDC.get(KEY);
        if (tid == null) {
            tid = UUID.randomUUID().toString().replace("-", "");
            MDC.put(KEY, tid);
        }
        return tid;
    }
}
