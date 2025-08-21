package com.zhoouon.starter.monitor.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhoouon.starter.monitor.annotation.Monitor;
import com.zhoouon.starter.monitor.utils.TraceId;
import com.zhoouon.starter.monitor.model.MonitorEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.time.Instant;
import java.util.*;

@Slf4j
@Aspect
@Component
public class MonitorAspect {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${spring.application.name:app}") private String app;
    @Value("${spring.profiles.active:dev}") private String env;

    @Pointcut("@within(com.zhoouon.starter.monitor.annotation.Monitor) || @annotation(com.zhoouon.starter.monitor.annotation.Monitor)")
    public void monitorPointcut() {}

    @Around("monitorPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        TraceId.ensure();

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Monitor ann = resolveAnnotation(pjp.getTarget().getClass(), method);

        boolean success = false;
        Throwable ex = null;
        Object ret = null;

        try {
            ret = pjp.proceed();
            success = true;
            return ret;
        } catch (Throwable t) {
            ex = t;
            throw t;
        } finally {
            long cost = System.currentTimeMillis() - begin;
            boolean slow = ann.timeoutMs() > 0 && cost >= ann.timeoutMs();

            MonitorEvent evt = MonitorEvent.builder()
                    .app(app)
                    .env(env)
                    .clazz(pjp.getTarget().getClass().getName())
                    .method(method.getName())
                    .logType(ann.logType())
                    .tags(Arrays.asList(ann.tags()))
                    .success(success)
                    .elapsedMs(cost)
                    .slow(slow)
                    .exceptionClass(ex == null ? null : ex.getClass().getName())
                    .exceptionMsg(ex == null ? null : ex.getMessage())
                    .traceId(org.slf4j.MDC.get(TraceId.KEY))
                    .thread(Thread.currentThread().getName())
                    .host(localHost())
                    .ts(Instant.now())
                    .build();

            // 核心：写一行 JSON 日志，交给 Kafka Appender 送走
            log.info("MON_EVENT {}", toJson(evt));
        }
    }

    private Monitor resolveAnnotation(Class<?> clazz, Method method) {
        Monitor m = method.getAnnotation(Monitor.class);
        return m != null ? m : clazz.getAnnotation(Monitor.class);
    }

    private String localHost() {
        try { return InetAddress.getLocalHost().getHostName(); }
        catch (Exception e) { return "unknown"; }
    }

    private String toJson(Object obj) {
        try { return MAPPER.writeValueAsString(obj); }
        catch (Exception e) { return "{}"; }
    }
}
