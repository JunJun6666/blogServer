package com.wang.server.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author WJ
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIN_KEY_USERNAME = "sub";
    private static final String CLAIN_KEY_CREATED = "created";

//    密钥(@value从yml拿)
    @Value("${jwt.secret}")
    private String secret;

//    失效时间(@value从yml拿)
    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * 根据用户信息生成token
     * userDetails是从spring-security里面拿到的
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        //生成一个荷载保存用户名
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIN_KEY_USERNAME , userDetails.getUsername());
        claims.put(CLAIN_KEY_CREATED , new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameByToken(String token){
        String username;
        try {
            //通过token去获取荷载
            Claims claims = getClaimsByToken(token);
            //获取用户名
            username = claims.getSubject();
        } catch (Exception e) {
            //如果发生异常，让它为空
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token , UserDetails userDetails){
        String userName = getUserNameByToken(token);
        //判断用户名是否和userDetails里面一致，和时间石否过期
        return Objects.equals(userName , userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsByToken(token);
        claims.put(CLAIN_KEY_CREATED , new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token){
        //拿到这个token的失效时间
        Date expireDate = getExpiredDateFromToken(token);
        //判断如果在当前时间前面，那肯定是失效的
        return expireDate.before(new Date());
    }

    /**
     * 取这个token的过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token){
        //从荷载中获取过期时间
        Claims claims = getClaimsByToken(token);
        //返回过期时间
        return claims.getExpiration();
    }

    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsByToken(String token){
        Claims  claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return claims;
    }

    /**
     * 根据荷载生成token
     * @param claims
     * @return
     */
    private String generateToken(Map<String , Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512 , secret)
                .compact();
    }

    /**
     * 生成失效时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
