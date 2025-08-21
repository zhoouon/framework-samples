package com.zhoouon.starter.monitor.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class MonitorEvent {
    private String app;
    private String env;
    private String clazz;
    private String method;
    private String logType;
    private List<String> tags;
    private boolean success;
    private Long elapsedMs;
    private boolean slow;
    private String exceptionClass;
    private String exceptionMsg;
    private String traceId;
    private String thread;
    private String host;
    private Instant ts;
    private Map<String, Object> args;
}
