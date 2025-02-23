package com.zhoouon.redis.bloom.config;

import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

/**
 * @Author: zhoudong
 * @Description:
 * @Date: 2023/4/3 16:29
 * @Version: 1.0.0
 **/
public class Test {
    public static void main(String[] args) {
        List<@Nullable String> list1 = Lists.newArrayList();
        List<@Nullable String> list2 = Lists.newArrayList();
        List<@Nullable String> list3 = Lists.newArrayList();
        list1.add("1");
        list1.add("1");
        list1.add("1");
        list1.add("1");
        list1.add("1");
        list1.forEach(str ->{
            if ("1".equals(str)) {
                list2.add(str);
                list3.add(str);
            }else {
                throw new RuntimeException("报异常了。。。。。");
            }
        });
        System.out.println("hello world!");
    }
}
