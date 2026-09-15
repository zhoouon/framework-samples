package com.zhoouon.starter.common.result;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result<T> {

    public static final String SUCCESS_CODE = "OK";
    public static final String SUCCESS_MESSAGE = "操作成功";

    private String code;

    private String message;

    private T data;

    private long timestamp;

}
