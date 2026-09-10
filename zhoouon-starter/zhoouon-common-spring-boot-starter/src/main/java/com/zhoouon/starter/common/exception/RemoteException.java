package com.zhoouon.starter.common.exception;

import java.io.Serial;

/**
 * 远程调用异常
 */
public class RemoteException extends SamplesApplicationException {

    @Serial
    private static final long serialVersionUID = -3095639653937569308L;

    public RemoteException() {
        super(null, null, ErrorCode.REMOTE_ERROR);
    }

    public RemoteException(ErrorCode errorCode) {
        this(errorCode, errorCode.getMessage(), null);
    }

    public RemoteException(ErrorCode errorCode, String message, Throwable throwable) {
        super(message, throwable, errorCode);
    }

    public RemoteException(String code, String message) {
        super(code, message, null);
    }
}
