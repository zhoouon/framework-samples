package com.zhoouon.starter.common.exception;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024-07-09 12:53
 * @Version: 1.0.0
 **/
public class ErrorCodeNotFoundException extends SamplesApplicationException{

    public ErrorCodeNotFoundException(String messageKey) {
        super(messageKey);
    }

    public ErrorCodeNotFoundException(String messageKey, Object... args) {
        super(messageKey, args);
    }
}
