package com.zhoouon.starter.web;

import com.zhoouon.starter.common.result.BaseResult;
import com.zhoouon.starter.common.toolkit.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Description:
 * 在web环境中即DDD的接口层自动封装接口返回格式
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    // @Autowired
    // private ObjectMapper objectMapper;

    /**
     *  此处可以通过判断决定哪些响应需要包装,有两种办法
     *  1. 指定需要返回的接口，就DailyMart而言，接口项目包的地址前缀都是 com.jianzh5.dailymart
     *  2. 如果返回的接口类型是springdoc的，则不转换，returnType.getDeclaringClass().getName().contains("springdoc")
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        // boolean supports = returnType.getContainingClass().getPackage().getName().startsWith("com.jianzh5.dailymart");
        // return supports;
        boolean supports = returnType.getDeclaringClass().getName().contains("springdoc");
        return !supports;

    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body == null) {
            return JsonUtils.obj2String(BaseResult.buildSuccessData(""));
        }
        if (body instanceof String) {
            // 当响应体是String类型时，使用ObjectMapper转换，因为Spring默认使用StringHttpMessageConverter处理字符串，不会将字符串识别为JSON
            // return objectMapper.writeValueAsString(ResultFactory.success(body));
            return JsonUtils.obj2String(BaseResult.buildSuccessData(body));
        }
        if (body instanceof BaseResult<?>) {
            // 已经包装过的结果无需再次包装
            return body;
        }
        // 对响应体进行包装
        return BaseResult.buildSuccessData(body);
    }
}
