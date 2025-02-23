package com.zhoouon.starter.common.toolkit;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Orika封装的工具类
 */
public enum OrikaMapperUtils {

    /**
     * 实例
     */
    INSTANCE;

    private static final MapperFactory FACTORY = new DefaultMapperFactory.Builder().build();

    /**
     * 缓存实例集合
     */
    private static final Map<String, MapperFacade> CACHE_MAPPER = new ConcurrentHashMap<>();

    /**
     * 转换实体函数
     * @param sourceEntity 源实体
     * @param targetClass  目标类对象
     * @param refMap       配置源类与目标类不同字段名映射
     * @param <S>          源泛型
     * @param <T>          目标泛型
     * @return 目标实体
     */
    public <S, T> T convert(S sourceEntity, Class<T> targetClass, Map<String, String> refMap) {
        if (sourceEntity == null) {
            return null;
        }
        return getMapperFacade(sourceEntity.getClass(), targetClass, refMap).map(sourceEntity, targetClass);
    }

    /**
     * 转换实体函数
     *
     * @param sourceEntity 源实体
     * @param targetClass  目标类对象
     * @param <S>          源泛型
     * @param <T>          目标泛型
     * @return 目标实体
     */
    public <S, T> T convert(S sourceEntity, Class<T> targetClass) {
        return convert(sourceEntity, targetClass, null);
    }

    /**
     * 转换实体集合函数
     *
     * @param sourceEntityList 源实体集合
     * @param targetClass      目标类对象
     * @param refMap           配置源类与目标类不同字段名映射
     * @param <S>              源泛型
     * @param <T>              目标泛型
     * @return 目标实体集合
     */
    public <S, T> List<T> convertList(List<S> sourceEntityList, Class<T> targetClass, Map<String, String> refMap) {
        if (sourceEntityList == null) {
            return null;
        }
        if (sourceEntityList.size() == 0) {
            return new ArrayList<>(0);
        }
        return getMapperFacade(sourceEntityList.get(0).getClass(), targetClass, refMap).mapAsList(sourceEntityList, targetClass);
    }

    /**
     * 转换实体集合函数
     *
     * @param sourceEntityList 源实体集合
     * @param targetClass      目标类对象
     * @param <S>              源泛型
     * @param <T>              目标泛型
     * @return 目标实体集合
     */
    public <S, T> List<T> convertList(List<S> sourceEntityList, Class<T> targetClass) {
        return convertList(sourceEntityList, targetClass, null);
    }

    /**
     * 注册属性
     * @param source 源类
     * @param target 目标类
     * @param refMap 属性转换
     */
    public <V, P> void register(Class<V> source, Class<P> target, Map<String, String> refMap) {
        if (CollectionUtils.isEmpty(refMap)) {
            FACTORY.classMap(source, target).byDefault().register();
        } else {
            ClassMapBuilder<V, P> classMapBuilder = FACTORY.classMap(source, target);
            refMap.forEach(classMapBuilder::field);
            classMapBuilder.byDefault().register();
        }
    }

    /**
     * 获取自定义映射
     *
     * @param source   源实体
     * @param target   目标实体
     * @param refMap   属性映射
     * @return 映射类对象
     */
    private <V, P> MapperFacade getMapperFacade(Class<V> source, Class<P> target, Map<String, String> refMap) {
        String mapKey = source.getCanonicalName() + ":" + target.getCanonicalName();
        if (CACHE_MAPPER.containsKey(mapKey)) {
            return CACHE_MAPPER.get(mapKey);
        }
        register(source, target, refMap);
        MapperFacade mapperFacade = FACTORY.getMapperFacade();
        CACHE_MAPPER.put(mapKey, mapperFacade);
        return mapperFacade;
    }

}
