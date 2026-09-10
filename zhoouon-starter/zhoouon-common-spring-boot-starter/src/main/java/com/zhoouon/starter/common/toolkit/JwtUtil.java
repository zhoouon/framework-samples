package com.zhoouon.starter.common.toolkit;

import cn.hutool.crypto.SecureUtil;
import com.zhoouon.starter.common.exception.BaseException;
import com.zhoouon.starter.common.exception.ErrorCode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

/**
 * JWT工具类
 */
public class JwtUtil {

    private static final String SECRET_KEY = "DailyMart-DDD";
    private static final long EXPIRATION_TIME = 6000_000; // 10 minutes in milliseconds

    public static String createJWT(String subject) throws JOSEException {
        String secret = SecureUtil.md5(SECRET_KEY);
        // 创建一个空的 JWT 构建器
        JWSSigner signer = new MACSigner(secret);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

        // 签名 JWT
        signedJWT.sign(signer);

        // 返回 JWT 字符串
        return signedJWT.serialize();
    }

    public static String getSubjectFromJWT(String jwt) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(jwt);

        JWSVerifier jwsVerifier = new MACVerifier(SecureUtil.md5(SECRET_KEY));

        if (!signedJWT.verify(jwsVerifier)) {
            throw new BaseException(ErrorCode.JWT_PARSE_ERROR.getCode(), "Token签名不合法");
        }

        // 验证过期时间
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        if (expirationTime == null || expirationTime.before(new Date())) {
            throw new BaseException(ErrorCode.JWT_PARSE_ERROR.getCode(), "Token已过期");
        }

        return signedJWT.getJWTClaimsSet().getSubject();
    }

    public static void main(String[] args) throws ParseException, JOSEException {
        // String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTA0ODM3NDksInN1YiI6ImppYW56aDUifQ.3E5-pYCcgt4hnwEVHKpvilg7WK04IgOOzfQTl9OQpiE";

        // String subjectFromJWT = getSubjectFromJWT(jwt);
        //
        // System.out.println("登录主体:" + subjectFromJWT);

    }

}
