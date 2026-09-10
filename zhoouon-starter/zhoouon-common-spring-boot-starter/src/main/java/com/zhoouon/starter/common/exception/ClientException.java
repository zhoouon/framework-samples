package com.zhoouon.starter.common.exception;

import java.io.Serial;

/**
 * 客户端异常
 */
public class ClientException extends SamplesApplicationException {

    @Serial
    private static final long serialVersionUID = 2792869211011068994L;

    public ClientException(String message) {
        this(ErrorCode.CLIENT_ERROR, null, message);
    }

    public ClientException(ErrorCode errorCode) {
        this(errorCode, null, errorCode.getMessage());
    }

    public ClientException(ErrorCode errorCode, String message) {
        this(errorCode, null, message);
    }

    public ClientException(ErrorCode errorCode, Throwable throwable, String message) {
        super(message, throwable, errorCode);
    }
}
