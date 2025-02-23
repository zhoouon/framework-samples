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
import java.nio.charset.Charset;

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
        try {
            Reader reader = response.body().asReader(Charset.defaultCharset());
            // Result<?> result = objectMapper.readValue(reader, objectMapper.constructType(Result.class));
            BaseResult<?> result = JsonUtils.reader2Obj(reader, BaseResult.class);
            return new RemoteException(result.getCode(), result.getMessage());
        } catch (IOException e) {
            log.error("Response转换异常", e);
            throw new RemoteException(ErrorCode.FEIGN_ERROR);
        }

    }
}
