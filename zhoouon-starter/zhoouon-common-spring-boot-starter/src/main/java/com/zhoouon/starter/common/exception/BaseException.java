package com.zhoouon.starter.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: zhoudong
 * @Description: 业务异常处理
 * @Date: 2024-07-09 12:52
 * @Version: 1.0.0
 **/
public class BaseException extends SamplesApplicationException {

    public BaseException(String messageKey) {
        super(messageKey);
        this.checkMessageKey(messageKey);
    }

    public BaseException(String messageKey, Object... args) {
        super(messageKey, args);
        this.checkMessageKey(messageKey);
    }

    public BaseException(String messageKey, Throwable cause) {
        super(messageKey, cause);
        this.checkMessageKey(messageKey);
    }

    public BaseException(String messageKey, Throwable cause, Object... args) {
        super(messageKey, cause, args);
        this.checkMessageKey(messageKey);
    }

    private void checkMessageKey(String messageKey) {
        String value = ExceptionCodeCache.get(messageKey);
        if (StringUtils.isEmpty(value)) {
            throw new ErrorCodeNotFoundException("{}未找到对应的定义，请在对应的模块message-source文件定义异常码", new Object[]{messageKey});
        }
    }
}
