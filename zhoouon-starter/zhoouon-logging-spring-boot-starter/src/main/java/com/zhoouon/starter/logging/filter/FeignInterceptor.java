package com.zhoouon.starter.logging.filter;

import com.zhoouon.starter.logging.utils.MDCTraceUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.Objects;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024/4/9 14:34
 * @Version: 1.0.0
 **/
public class FeignInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 传递请求相关header
        if (requestAttributes != null) {
            HttpServletRequest request = (HttpServletRequest) requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    // 跳过 content-length
                    if (Objects.equals("content-length", name)){
                        continue;
                    }
                    String value = request.getHeader(name);
                    requestTemplate.header(name, value);
                }
            }
        }
        // 传递日志追踪的traceId
        String traceId = MDCTraceUtils.getTraceId();
        if (StringUtils.isNotBlank(traceId)) {
            requestTemplate.header(MDCTraceUtils.TRACE_ID_HEADER, traceId);
        }
    }

}
