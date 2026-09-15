package com.zhoouon.starter.logging.filter;

import com.alibaba.fastjson.JSONObject;
import com.zhoouon.starter.logging.utils.MDCTraceUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: zhoudong
 * @Description: web拦截器，传递traceId
 * @Date: 2024/4/6 18:48
 * @Version: 1.0.0
 **/
@Slf4j
public class WebTraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            log.info("request param is {}", JSONObject.toJSONString(parameterMap));
            String traceId = request.getHeader(MDCTraceUtils.TRACE_ID_HEADER);
            if (StringUtils.isEmpty(traceId)) {
                MDCTraceUtils.addTrace();
            } else {
                MDCTraceUtils.putTrace(traceId);
            }
            filterChain.doFilter(request, response);
        } finally {
            MDCTraceUtils.removeTrace();
        }
    }
}
