package com.zhoouon.starter.common.exception;

import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @Author: zhoudong
 * @Description: 公共异常封装
 * @Date: 2024-07-09 11:43
 * @Version: 1.0.0
 **/

public class SamplesApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String messageKey;
    private String resolvedMessage;
    private Object[] args;
    private boolean dataInconsistent = false;

    public SamplesApplicationException(String messageKey) {
        this.messageKey = messageKey;
    }

    public SamplesApplicationException(String messageKey, Object... args) {
        this.messageKey = messageKey;
        this.args = args;
    }

    public SamplesApplicationException(String messageKey, Throwable cause) {
        super(cause);
        this.messageKey = messageKey;
    }

    public SamplesApplicationException(String messageKey, Throwable cause, Object... args) {
        super(cause);
        this.messageKey = messageKey;
        this.args = args;
    }

    public SamplesApplicationException(String messageKey, boolean isDataInconsistant) {
        this.messageKey = messageKey;
        this.dataInconsistent = isDataInconsistant;
    }

    public SamplesApplicationException(String messageKey, boolean isDataInconsistant, Object... args) {
        this.messageKey = messageKey;
        this.args = args;
        this.dataInconsistent = isDataInconsistant;
    }

    public SamplesApplicationException(String messageKey, Throwable cause, boolean isDataInConsistent) {
        super(cause);
        this.messageKey = messageKey;
        this.dataInconsistent = isDataInConsistent;
    }

    public SamplesApplicationException(String messageKey, Throwable cause, boolean isDataInConsistent, Object... args) {
        super(cause);
        this.messageKey = messageKey;
        this.args = args;
        this.dataInconsistent = isDataInConsistent;
    }

    public Throwable getRootCause() {
        Throwable rootCause = null;

        for (Throwable cause = this.getCause(); cause != null && cause != rootCause; cause = cause.getCause()) {
            rootCause = cause;
        }

        return rootCause;
    }

    public Throwable getMostSpecificCause() {
        Throwable rootCause = this.getRootCause();
        return (Throwable) (rootCause != null ? rootCause : this);
    }

    public boolean contains(Class<?> exType) {
        if (exType == null) {
            return false;
        } else if (exType.isInstance(this)) {
            return true;
        } else {
            Throwable cause = this.getCause();
            if (cause == this) {
                return false;
            } else if (cause instanceof SamplesApplicationException) {
                return ((SamplesApplicationException) cause).contains(exType);
            } else {
                while (cause != null) {
                    if (exType.isInstance(cause)) {
                        return true;
                    }
                    if (cause.getCause() == cause) {
                        break;
                    }
                    cause = cause.getCause();
                }
                return false;
            }
        }
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public String getResolvedMessage() {
        return this.resolvedMessage;
    }

    public void setResolvedMessage(String resolvedMessage) {
        this.resolvedMessage = resolvedMessage;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public boolean isDataInconsistent() {
        return this.dataInconsistent;
    }

    public void setDataInconsistent(boolean dataInconsistent) {
        this.dataInconsistent = dataInconsistent;
    }

    public String getMessage() {
        StringBuffer buf = new StringBuffer();
        if (StringUtils.hasText(super.getMessage())) {
            buf.append(super.getMessage());
            buf.append("\t");
        }

        if (StringUtils.hasText(this.getResolvedMessage())) {
            buf.append(this.getResolvedMessage());
        } else if (StringUtils.hasText(this.getMessageKey())) {
            buf.append(this.getMessageKey());
            if (this.getArgs() != null) {
                buf.append(Arrays.toString(this.getArgs()));
            }
        }
        return buf.toString();
    }
}
