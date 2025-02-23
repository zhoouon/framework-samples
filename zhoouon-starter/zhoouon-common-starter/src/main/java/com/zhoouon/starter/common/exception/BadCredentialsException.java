package com.zhoouon.starter.common.exception;

import java.io.Serial;

/**
 * 客户端异常
 */
public class BadCredentialsException extends ClientException {

    @Serial
    private static final long serialVersionUID = -5653832166622573877L;

    /**
     * Constructs a <code>BadCredentialsException</code> with the specified message.
     *
     * @param message the detail message.
     */
    public BadCredentialsException(String message) {
        super(ErrorCode.BAD_CREDENTIALS_EXPIRED, null, message);
    }

    /**
     * @param message the detail message.
     * @param throwable the detail Throwable.
     */
    public BadCredentialsException(String message, Throwable throwable) {
        super(ErrorCode.BAD_CREDENTIALS_EXPIRED, throwable, message);
    }
}
