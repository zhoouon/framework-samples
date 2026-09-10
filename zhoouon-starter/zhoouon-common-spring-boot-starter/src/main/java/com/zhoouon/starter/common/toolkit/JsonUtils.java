package com.zhoouon.starter.common.toolkit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Objects;

@UtilityClass
@Slf4j
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperInstance.INSTANCE.getObjectMapper();

    /**
     * 对象转Json格式化
     * @param obj 对象
     * @return Json格式化
     */
    public <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Parse Object to String error : {}", e.getMessage());
            return null;
        }
    }

    public <T> byte[] obj2Byte(T obj) throws JsonProcessingException {
        if (obj == null) {
            return null;
        }
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串)
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    public <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Parse Object to String error : {}", e.getMessage());
            return null;
        }
    }

    public <T> T reader2Obj(Reader reader, Class<T> tClass) throws IOException {

        if (reader == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(reader, tClass);
    }

    public <T> T inputStream2Obj(InputStream input, Class<T> tClass) throws IOException {
        if (input == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(input, tClass);
    }

    public <T> T byte2Obj(byte[] bytes, Class<T> tClass) {
        if (bytes == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(bytes, tClass);
        } catch (IOException e) {
            log.error("Parse byte to Object error : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 字符串转换为自定义对象
     * @param str 要转换的字符串
     * @param clazz 自定义对象的class对象
     * @return 自定义对象
     */
    @SuppressWarnings("unchecked")
    public <T> T string2Obj(String str, Class<T> clazz) {
        if (Objects.isNull(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : OBJECT_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            log.error("Parse String to Object error : {}", e.getMessage());
            return null;
        }
    }

    public <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : OBJECT_MAPPER.readValue(str, typeReference));
        } catch (IOException e) {
            log.error("Parse String to Object error", e);
            return null;
        }
    }

    public <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazz) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClazz, elementClazz);
        try {
            return OBJECT_MAPPER.readValue(str, javaType);
        } catch (IOException e) {
            log.error("Parse String to Object error : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 格式转换
     * @param data 待转对象
     * @param javaType javaType
     * @return Object
     */
    public Object convertValue(Object data, JavaType javaType) {
        return OBJECT_MAPPER.convertValue(data, javaType);
    }

}
