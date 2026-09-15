package com.zhoouon.starter.common.enums;

/**
 * 删除标识枚举值
 */
public enum DelEnum {

    /** 正常状态  **/
    NORMAL(0),
    /** 删除状态  **/
    DELETED(1);

    private final Integer statusCode;

    DelEnum(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer code() {
        return this.statusCode;
    }

    public String strCode() {
        return String.valueOf(this.statusCode);
    }

}
