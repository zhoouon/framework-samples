package com.zhoouon.starter.monitor.filter;

import java.io.IOException;

import com.zhoouon.starter.monitor.utils.TraceId;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TraceIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            TraceId.ensure();
            chain.doFilter(req, res);
        } finally {
            MDC.remove(TraceId.KEY);
        }
    }
}
