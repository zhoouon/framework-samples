package com.zhoouon.starter.openfeign;

import com.zhoouon.starter.common.exception.ErrorCode;
import com.zhoouon.starter.common.exception.RemoteException;
import com.zhoouon.starter.common.exception.SamplesApplicationException;
import com.zhoouon.starter.common.result.Result;
import feign.FeignException;
import feign.codec.DecodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Feign的全局异常处理，与常规的全局异常处理类分开
 */
@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE) // 优先级
@ResponseStatus(code = HttpStatus.BAD_REQUEST) // 统一 HTTP 状态码
public class SamplesFeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public Result<?> handleFeignException(FeignException e) {
        // log.error("FeignException: ", e);
        return new Result<Void>()
                .setCode(ErrorCode.REMOTE_ERROR.getCode())
                .setMessage(e.getMessage())
                .setTimestamp(System.currentTimeMillis());
    }

    @ExceptionHandler(DecodeException.class)
    public Result<?> handleDecodeException(DecodeException e) {
        log.error("Feign Decode Error: ", e);
        Throwable cause = e.getCause();
        if (cause instanceof SamplesApplicationException) {
            RemoteException remoteException = (RemoteException) cause;
            // 上游符合全局响应包装约定的再次抛出即可
            return new Result<Void>()
                    .setCode(remoteException.getMessageKey())
                    .setMessage(remoteException.getMessage())
                    .setTimestamp(System.currentTimeMillis());
        }
        // 全部转换成RemoteException
        return new Result<Void>()
                .setCode(ErrorCode.REMOTE_ERROR.getCode())
                .setMessage(e.getMessage())
                .setTimestamp(System.currentTimeMillis());
    }

}
