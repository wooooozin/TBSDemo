package com.example.tablebooker.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.tablebooker.user.exception.UnauthorizedAccessException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JWTUtils {

    private static String KEY = "booker";
    public static String getIssuer(String token) {
        String issuer = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
                .build()
                .verify(token)
                .getIssuer();
        return issuer;
    }

    public Long getId(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
                .build()
                .verify(token);
        String userId = jwt.getClaim("userId").asString();
        if (userId == null) {
            throw new UnauthorizedAccessException("유효하지 않는 토큰입니다.");
        }
        return Long.valueOf(userId);
    }
}
