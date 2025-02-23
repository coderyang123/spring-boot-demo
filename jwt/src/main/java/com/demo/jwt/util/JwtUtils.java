package com.demo.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.jwt.domain.User;
import java.util.Calendar;

/**
 * JWT工具类
 *
 * @author yueyang
 * @since 2021-08-03 21:08:00
 */
public class JwtUtils {

  /**
   * 获取token
   *
   * @param user 用户实体
   * @return token
   */
  public static String getToken(User user) {
    // 默认令牌过期时间7天
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.DATE, 7);

    JWTCreator.Builder builder = JWT.create();
    builder.withClaim("userId", user.getId()).withClaim("username", user.getUsername());

    return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(user.getPassword()));
  }

  /**
   * 验证token合法性 成功返回token
   *
   * @param token token
   * @return DecodedJWT
   */
  public static DecodedJWT verify(String token) {
    // 从数据库获取登录用户密码，这里模拟一个
    String password = "123456";
    JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();

    return build.verify(token);
  }
}
