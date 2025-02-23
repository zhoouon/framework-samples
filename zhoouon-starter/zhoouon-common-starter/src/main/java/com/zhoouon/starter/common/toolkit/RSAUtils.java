package com.zhoouon.starter.common.toolkit;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.ObjectUtils;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA工具类
 **/
@UtilityClass
public class RSAUtils {

    // 密钥大小为位数
    private static final Integer KEY_SIZE = 1024;

    private static final String RSA_ENC = "RSA";

    /**
     * 生成RSA密钥对
     * @author Jam
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @date 2022/7/12 14:48
     */
    @SneakyThrows
    public Map<String, String> genRSAKeyPair() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ENC);
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        String publicKeyString = base64Encode(publicKey.getEncoded());
        // String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        String privateKeyString = base64Encode(privateKey.getEncoded());
        Map<String, String> keyMap = new HashMap<>(2);
        keyMap.put("publicKey", publicKeyString);
        keyMap.put("privateKey", privateKeyString);
        return keyMap;
    }

    private String base64Encode(byte[] key) {
        return Base64.getEncoder().encodeToString(key);
    }

    private byte[] base64Decode(String key) {
        return Base64.getDecoder().decode(key);
    }

    /**
     * 公钥加密
     * @author Jam
     * @param encryptStr 待加密内容
     * @param publicKey  公钥
     */
    @SneakyThrows
    public String rsaEncrypt(String encryptStr, String publicKey) {

        if (ObjectUtils.isEmpty(encryptStr) || ObjectUtils.isEmpty(publicKey)) {
            return null;
        }
        byte[] decoded = base64Decode(publicKey);

        RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance(RSA_ENC).generatePublic(new X509EncodedKeySpec(decoded));

        Cipher cipher = Cipher.getInstance(RSA_ENC);

        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

        return base64Encode(cipher.doFinal(encryptStr.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 私钥解密
     * @author jam
     * @date 2023/11/14 20:41
     * @param decryptStr 待解密内容
     * @param privateKey 私钥
     */
    @SneakyThrows
    public static String rsaDecrypt(String decryptStr, String privateKey) {
        if (ObjectUtils.isEmpty(decryptStr) || ObjectUtils.isEmpty(privateKey)) {
            return null;
        }
        // 64位解码加密后的字符串
        byte[] inputByte = base64Decode(decryptStr);
        // base64编码的私钥
        byte[] decoded = base64Decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA_ENC).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // RSA解密
        Cipher cipher = Cipher.getInstance(RSA_ENC);
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        return new String(cipher.doFinal(inputByte));
    }

    public static void main(String[] args) {
        Map<String, String> initKeyPair = RSAUtils.genRSAKeyPair();
        String privateKey = initKeyPair.get("privateKey");
        System.out.println("privateKey: " + privateKey);

        String publicKey = initKeyPair.get("publicKey");
        System.out.println("publicKey: " + publicKey);

        Map<String, String> maps = new HashMap<>();
        maps.put("userName", "javadaily");
        maps.put("password", "000000");
        String json = JsonUtils.obj2String(maps);

        System.out.println("json:" + json);

        String rsaEncrypt = rsaEncrypt(json, publicKey);
        System.out.println("rsaEncrypt:" + rsaEncrypt);

        String rsaDecrypt = rsaDecrypt(rsaEncrypt, privateKey);
        System.out.println("rsaDecrypt:" + rsaDecrypt);

        //
        // String str="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTc4NjE1OTAsInVzZXJuYW1lIjoiamlhbnpoYW5nMTEifQ.hrMaDpTHCDVyR3mMfXAuuEkjDSmoG9K93zsbWHxFeFY";
        // System.out.println("privateKey:"+privateKey);
        // System.out.println("publicKey:"+publicKey);
        // //私钥加密
        // String encryptStr= RSAUtils.encrypt(str,privateKey);
        // System.out.println("加密后字符串:"+encryptStr);
        // //公钥解密
        // String decryptStr= RSAUtils.decrypt(encryptStr,publicKey);
        // System.out.println("解密后字符串:"+decryptStr);

    }

}
