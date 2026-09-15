package com.zhoouon.starter.web.interceptor;

import com.zhoouon.starter.web.RequestHeaderHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;

/**
 * 从网关请求头中获取用户信息,将其设置到RequestHeaderHolder中
 */
@Slf4j
public class RequestHeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        Enumeration<String> headerNames = request.getHeaderNames();
        RequestHeaderHolder requestHeaderHolder = RequestHeaderHolder.getInstance();

        // 重新设置请求头
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            requestHeaderHolder.set(key, request.getHeader(key));
        }
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {

        RequestHeaderHolder.getInstance().clear();
    }
}
