package com.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtApplicationTests {

  @Test
  void contextLoads() {}

  @Test
  void jwtTest() {
    // 生产token
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.SECOND, 100);

    String token =
        JWT.create()
            // 设置载体
            .withClaim("username", "zhangsan")
            .withClaim("userid", 12)
            // token过期时间
            .withExpiresAt(instance.getTime())
            // 签名
            .sign(Algorithm.HMAC256("123456"));
    System.out.println(token);
  }

  @Test
  void contextLoads2() {
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
    // 验证token
    DecodedJWT verify =
        jwtVerifier.verify(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mjc5OTc2NTgsInVzZXJpZCI6MTIsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.uMM2PIGPFtuyvEK46gWACbRdwn1pTj_GsK67NW4P__k");
    // 验证成功后取出载体
    Map<String, Claim> claims = verify.getClaims();

    System.out.println(verify.getClaims().get("username").toString());
    System.out.println(verify.getClaims().get("userid").asInt());
  }
}
