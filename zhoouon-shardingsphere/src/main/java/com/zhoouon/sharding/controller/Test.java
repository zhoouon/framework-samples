package com.zhoouon.sharding.controller;

import java.io.File;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024/9/29 14:32
 * @Version: 1.0.0
 **/
public class Test {

    public static void main(String[] args) {
        File file = new File("temp");
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);
    }
}
