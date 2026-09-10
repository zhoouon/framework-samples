package com.zhoouon.starter.common.aop;

import com.alibaba.fastjson.JSON;
import com.zhoouon.starter.common.exception.BaseException;
import com.zhoouon.starter.common.result.BaseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024-07-10 11:01
 * @Version: 1.0.0
 **/
@Aspect
public class ExceptionAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);
    // 排除不需要拦截的方法
    Set<String> excludeSet = new HashSet<String>() {
        {
            this.add("queryUser");
            this.add("queryTenant");
            this.add("listTenantRole");
        }
    };

    public ExceptionAspect() {
    }

    @Pointcut("@annotation(com.zhoouon.starter.common.action.Function)")
    private void exceptionCatching() {
    }

    @Around("com.zhoouon.starter.common.aop.ExceptionAspect.exceptionCatching()")
    public Object catchException(ProceedingJoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        try {
            if (this.excludeSet.contains(methodName)) {
                return joinPoint.proceed();
            } else {
                BaseResult result = (BaseResult) joinPoint.proceed();
                if (ObjectUtils.isEmpty(result)) {
                    return BaseResult.buildSuccessData();
                } else {
                    return result.isSuccess() ? BaseResult.buildSuccessData(JSON.toJSON(result.getData())) : result;
                }
            }
        } catch (BaseException var5) {
            BaseException e = var5;
            logger.warn("{}类的{}方法，发生系统异常，异常码{}，异常信息{}", new Object[]{className, methodName, e.getMessageKey(), e.getMessage()});
            logger.error("异常堆栈信息：", e);
            return BaseResult.buildFailWithCode(e.getMessageKey(), e.getArgs());
        } catch (Throwable var6) {
            Throwable e = var6;
            logger.warn("{}类的{}方法，发生系统异常", className, methodName);
            logger.error("异常堆栈信息：", e);
            return BaseResult.buildFail(e);
        }
    }
}
