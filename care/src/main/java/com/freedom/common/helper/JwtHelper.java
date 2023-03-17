package com.freedom.common.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHelper {

    private final static String USER_NAME = "user_name";
    private final static String USER_ROLE = "user_role";
    private final static String ISSUER = "freedomcz.com";
    private final static long EXPIRATION_TIME = 8640000000L; //86400000 = 24 * 3600 * 1000 * 10

    // region jwt key
    private static String jwtKey;

    public static String getJwtKey() {
        return jwtKey;
    }

    @Value("${freedom.jwt-key:12345678901234567890123456789012345678901234567890}")
    public void setJwtKey(String value) {
        jwtKey = value;
    }
    // endregion

    /**
     * 解析JWT
     *
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(getJwtKey())
                    .build()
                    .parseClaimsJws(jsonWebToken);
            return jws.getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 创建JWT
     *
     * @return
     */
    public static String createJWT(String username, String role) {
        return Jwts.builder()
                .setIssuer(ISSUER)
                .claim(USER_NAME, username)
                .claim(USER_ROLE, role)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, getJwtKey())
                .compact();
    }

}
