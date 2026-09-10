package com.zhoouon.starter.common.result;

import com.zhoouon.starter.common.exception.ExceptionCodeCache;
import com.zhoouon.starter.common.exception.SamplesApplicationException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Optional;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024-07-09 11:42
 * @Version: 1.0.0
 **/
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 3236020669003158216L;
    private static final String SUCCESS_TYPE = "S";
    private static final String FAIL_TYPE = "E";
    private static final String UNKNOWN_TYPE = "R";
    private static final String SUCCESS_CODE = "00000000";
    private static final String SUCCESS_MESSAGE = "成功";
    private static final String SUCCESS_TIPS = "操作成功";
    public static final String DEFAULT_ERROR_CODE = "99999999";
    public static final String DEFAULT_FAIL_TIPS = "系统异常，请联系管理员";
    private static final Integer ERROR_MESSAGE_MAX_LENGTH = 1000;
    private String type;
    private String code;
    private String message;
    private String tips;
    private Long timestamp;
    private T data;

    public BaseResult() {
    }

    public BaseResult(String code, String message, Long timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    private BaseResult(Builder<T> builder) {
        this.type = builder.type;
        this.code = builder.code;
        this.message = builder.message;
        this.tips = builder.tips;
        this.data = builder.data;
    }

    public static <T> BaseResult<T> buildSuccessData() {
        return (new Builder()).buildType("S").buildCode("00000000").buildMessage("成功").buildTips("操作成功").build();
    }

    public static <T> BaseResult<T> buildSuccessData(T data) {
        return (new Builder()).buildType("S").buildCode("00000000").buildMessage("成功").buildTips("操作成功").buildData(data).build();
    }

    public static <T> BaseResult<T> buildFailWithCode(String errorCode) {
        String errorMessage = (String) Optional.ofNullable(ExceptionCodeCache.get(errorCode)).orElse("");
        return buildFail(errorCode, errorMessage);
    }

    public static <T> BaseResult<T> buildFailWithCode(String errorCode, Object... args) {
        String errorMessage = (String)Optional.ofNullable(ExceptionCodeCache.get(errorCode, args)).orElse("");
        return buildFail(errorCode, errorMessage);
    }

    public static <T> BaseResult<T> buildFail(String errorCode, String errorMessage) {
        String tips;
        if ("99999999".equals(errorCode)) {
            tips = "系统异常，请联系管理员";
        } else {
            tips = errorMessage;
        }

        return (new Builder()).buildType("E").buildCode(errorCode).buildMessage(errorMessage).buildTips(tips).build();
    }

    public static <T> BaseResult<T> buildFail(String errorCode, String errorMessage, String tips) {
        return (new Builder()).buildType("E").buildCode(errorCode).buildMessage(errorMessage).buildTips(tips).build();
    }

    public static <T> BaseResult<T> buildFail(String errorCode, String errorMessage, String tips, T data) {
        return (new Builder()).buildType("E").buildCode(errorCode).buildMessage(errorMessage).buildTips(tips).buildData(data).build();
    }

    public static <T> BaseResult<T> buildFailWithMessage(String errorMessage) {
        return (new Builder()).buildType("E").buildCode("99999999").buildMessage(errorMessage).buildTips("系统异常，请联系管理员").build();
    }

    public static <T> BaseResult<T> buildFailWithMessageAndTips(String errorMessage, String tips) {
        return (new Builder()).buildType("E").buildCode("99999999").buildMessage(errorMessage).buildTips(tips).build();
    }

    public static <T> BaseResult<T> buildFailWithMessageAndData(String errorMessage, T data) {
        return (new Builder()).buildType("E").buildCode("99999999").buildMessage(errorMessage).buildData(data).build();
    }

    public static <T> BaseResult<T> buildFail(SamplesApplicationException exception) {
        String errorCode = exception.getMessageKey();
        String errorMessage = exception.getResolvedMessage();
        if (StringUtils.isBlank(errorMessage)) {
            errorMessage = getMessageFromException(exception);
        }

        return buildFail(errorCode, errorMessage);
    }

    public static <T> BaseResult<T> buildFail(Throwable throwable) {
        return throwable instanceof SamplesApplicationException ? buildFail((SamplesApplicationException)throwable) : (new Builder()).buildType("E").buildCode("99999999").buildMessage("系统异常，请联系管理员").buildTips("系统异常，请联系管理员").build();
    }

    public static <T> BaseResult<T> buildUnknown() {
        return (new Builder()).buildType("R").buildMessage("交易结果未知").buildTips("交易结果未知，请联系业务人员手动处理").build();
    }

    public static Boolean isSuccess(BaseResult baseResult) {
        return "S".equals(baseResult.getType());
    }

    public static Boolean isSuccess(String type) {
        return "S".equals(type);
    }

    public Boolean isSuccess() {
        return "S".equals(this.getType());
    }

    public static Boolean isFail(String type) {
        return "E".equals(type);
    }

    public static Boolean isUnknown(String type) {
        return "R".equals(type);
    }

    public String getType() {
        return this.type;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTips() {
        return this.tips;
    }

    public T getData() {
        return this.data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static String getMessageFromException(Throwable throwable) {
        String errorMessage = throwable.getMessage();
        if (errorMessage.length() > ERROR_MESSAGE_MAX_LENGTH) {
            errorMessage = errorMessage.substring(0, ERROR_MESSAGE_MAX_LENGTH - 1);
        }

        return errorMessage;
    }

    public static class Builder<T> {
        private String type;
        private String code;
        private String message;
        private String tips;
        private T data;

        public Builder() {
        }

        public Builder(BaseResult<T> baseResult) {
            this.type = baseResult.type;
            this.code = baseResult.code;
            this.message = baseResult.message;
            this.tips = baseResult.tips;
            this.data = baseResult.data;
        }

        public Builder buildType(String type) {
            this.type = type;
            return this;
        }

        public Builder buildCode(String code) {
            this.code = code;
            return this;
        }

        public Builder buildMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder buildTips(String tips) {
            this.tips = tips;
            return this;
        }

        public Builder buildData(T data) {
            this.data = data;
            return this;
        }

        public BaseResult<T> build() {
            return new BaseResult(this);
        }
    }
}
