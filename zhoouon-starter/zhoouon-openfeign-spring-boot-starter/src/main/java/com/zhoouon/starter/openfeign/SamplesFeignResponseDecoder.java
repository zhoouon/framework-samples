package com.zhoouon.starter.openfeign;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zhoouon.starter.common.exception.ErrorCode;
import com.zhoouon.starter.common.exception.RemoteException;
import com.zhoouon.starter.common.result.BaseResult;
import com.zhoouon.starter.common.toolkit.JsonUtils;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Description:
 * 自定义Feign解码器,解决内部调用每次都需要使用包装类
 * 只有带返回值的才会经过此处理器，这也要求业务接口尽量不要直接返回 void 结果
 */
public class SamplesFeignResponseDecoder implements Decoder {

    // private final ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        // 与 GlobalResponseBodyAdvice / BaseResult 保持一致的响应结构
        BaseResult<?> result = JsonUtils.inputStream2Obj(response.body().asInputStream(), BaseResult.class);

        if (result != null && Boolean.TRUE.equals(result.isSuccess())) {
            JavaType javaType = TypeFactory.defaultInstance().constructType(type);
            return JsonUtils.convertValue(result.getData(), javaType);
        } else {
            // 若不成功，抛出业务异常，注意此处的异常会在 DecodeException 中被捕获
            String code = result == null ? ErrorCode.REMOTE_ERROR.getCode() : result.getCode();
            String message = result == null ? ErrorCode.REMOTE_ERROR.getMessage() : result.getMessage();
            throw new RemoteException(code, message);
        }
    }
}
