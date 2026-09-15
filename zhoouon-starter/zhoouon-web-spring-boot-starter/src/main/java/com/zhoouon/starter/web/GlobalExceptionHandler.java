package com.zhoouon.starter.web;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.zhoouon.starter.common.exception.ErrorCode;
import com.zhoouon.starter.common.exception.SamplesApplicationException;
import com.zhoouon.starter.common.result.BaseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * 全局异常处理器
 * @author Javadaily
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理参数验证异常
    @SneakyThrows
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class, ValidationException.class})
    public BaseResult<Void> handleValidException(HttpServletRequest request, Exception e) {
        String exceptionStr = "参数校验异常";
        if (e instanceof MethodArgumentNotValidException ex) {
            BindingResult bindingResult = ex.getBindingResult();
            FieldError firstFieldError = CollectionUtil.getFirst(bindingResult.getFieldErrors());

            exceptionStr = Optional.ofNullable(firstFieldError)
                    .map(FieldError::getDefaultMessage)
                    .orElse(StrUtil.EMPTY);

        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;

            ConstraintViolation<?> firstConstraintViolation = CollectionUtil.getFirst(ex.getConstraintViolations());

            exceptionStr = Optional.ofNullable(firstConstraintViolation)
                    .map(ConstraintViolation::getMessage)
                    .orElse(StrUtil.EMPTY);

        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            ObjectError firstObjectError = CollectionUtil.getFirst(ex.getAllErrors());

            exceptionStr = Optional.ofNullable(firstObjectError)
                    .map(ObjectError::getDefaultMessage)
                    .orElse(StrUtil.EMPTY);
        }

        log.error("[{}] {} [ex] {}", request.getMethod(), getUrl(request), exceptionStr);
        return BaseResult.buildFail(ErrorCode.CLIENT_ERROR.getCode(), ErrorCode.CLIENT_ERROR.getMessage(),exceptionStr);
    }

    // 处理自定义异常
    @ExceptionHandler(value = {SamplesApplicationException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult<Void> handleAbstractException(HttpServletRequest request, SamplesApplicationException ex) {
        String requestURL = getUrl(request);
        log.error("[{}] {} [ex] {}", request.getMethod(), requestURL, ex.toString());
        if (ex.getCause() != null) {
            log.error("[{}] {} [ex] {}", request.getMethod(), requestURL, ex, ex.getCause());
        }
        return BaseResult.buildFail(ex);
    }

    // 兜底处理
    @ExceptionHandler(value = Throwable.class)
    public BaseResult<Void> handleThrowable(HttpServletRequest request, Throwable throwable) {
        log.error("[{}] {} ", request.getMethod(), getUrl(request), throwable);
        return BaseResult.buildFail(ErrorCode.SERVICE_ERROR.getCode(), ErrorCode.SERVICE_ERROR.getMessage());
    }

    private String getUrl(HttpServletRequest request) {
        if (StrUtil.isEmpty(request.getQueryString())) {
            return request.getRequestURL().toString();
        }
        return request.getRequestURL().toString() + "?" + request.getQueryString();
    }
}
