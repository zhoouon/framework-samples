package com.zhoouon.starter.openfeign;

import com.zhoouon.starter.common.exception.ErrorCode;
import com.zhoouon.starter.common.exception.RemoteException;
import com.zhoouon.starter.common.result.BaseResult;
import com.zhoouon.starter.common.toolkit.JsonUtils;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 * 自定义Feign的异常解码器
 */
@Slf4j
public class SamplesFeignErrorDecoder implements ErrorDecoder {

    // private final ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();

    /**
     * OpenFeign的异常解析
     * @param methodKey 方法名
     * @param response 响应体
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() == null) {
            return new RemoteException(ErrorCode.FEIGN_ERROR);
        }
        try {
            Reader reader = response.body().asReader(StandardCharsets.UTF_8);
            BaseResult<?> result = JsonUtils.reader2Obj(reader, BaseResult.class);
            if (result == null) {
                return new RemoteException(ErrorCode.FEIGN_ERROR);
            }
            return new RemoteException(result.getCode(), result.getMessage());
        } catch (IOException e) {
            log.error("Response转换异常", e);
            throw new RemoteException(ErrorCode.FEIGN_ERROR);
        }

    }
}
