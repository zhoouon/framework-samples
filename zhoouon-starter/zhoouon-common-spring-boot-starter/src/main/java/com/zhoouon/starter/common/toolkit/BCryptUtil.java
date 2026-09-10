package com.zhoouon.starter.common.toolkit;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {

    /**
     * 密码加密
     * @param plainTextPassword 明文
     * @return 加密密钥
     */
    public static String hashPassword(String plainTextPassword) {

        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * 验证密码
     * @param plainTextPassword 明文
     * @param hashedPassword 加密密文
     * @return
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

}
