package com.zhoouon.encrypt.rsa;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author: zhoudong
 * @Description:
 * @Date: 2024/7/26 14:25
 * @Version: 1.0.0
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        // 随机生成一对密钥（包含公钥和私钥）
        KeyPair keyPair = RSAUtils.generateKeyPair();
        // 获取 公钥 和 私钥
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey priKey = keyPair.getPrivate();

        // 保存 公钥 和 私钥
        RSAUtils.saveKeyForEncodedBase64(pubKey, new File("pub.txt"));
        RSAUtils.saveKeyForEncodedBase64(priKey, new File("pri.txt"));

        /*
         * 上面代码是事先生成密钥对保存,
         * 下面代码是在实际应用中, 客户端和服务端分别拿现成的公钥和私钥加密/解密数据。
         */

        // 原文数据
        String data = "你好, World!";

        // 客户端: 加密
        byte[] cipherData = clientEncrypt(data.getBytes(), new File("pub.txt"));
        // 服务端: 解密
        byte[] plainData = serverDecrypt(cipherData, new File("pri.txt"));

        // 输出查看原文
        System.out.println(new String(plainData));  // 结果打印: 你好, World!
    }

    /**
     * 客户端加密, 返回加密后的数据
     */
    private static byte[] clientEncrypt(byte[] plainData, File pubFile) throws Exception {
        // 读取公钥文件, 创建公钥对象
        PublicKey pubKey = RSAUtils.getPublicKey(IOUtils.readFile(pubFile));

        // 用公钥加密数据
        byte[] cipher = RSAUtils.encrypt(plainData, pubKey);

        return cipher;
    }

    /**
     * 服务端解密, 返回解密后的数据
     */
    private static byte[] serverDecrypt(byte[] cipherData, File priFile) throws Exception {
        // 读取私钥文件, 创建私钥对象
        PrivateKey priKey = RSAUtils.getPrivateKey(IOUtils.readFile(priFile));

        // 用私钥解密数据
        byte[] plainData = RSAUtils.decrypt(cipherData, priKey);

        return plainData;
    }

}
