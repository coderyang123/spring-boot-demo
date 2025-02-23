package com.demo.jwt.common.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.demo.jwt.util.JwtUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT拦截器
 *
 * @author yueyang
 * @since 2021-08-03 21:13:00
 */
public class JwtInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    String token = request.getHeader("token");

    try {
      JwtUtils.verify(token);
    } catch (SignatureVerificationException e) {
      e.printStackTrace();
      System.out.println("无效签名");
      return false;
    } catch (TokenExpiredException e) {
      e.printStackTrace();
      System.out.println("token过期");
      return false;
    } catch (AlgorithmMismatchException e) {
      e.printStackTrace();
      System.out.println("token算法不一致");
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("token无效");
      return false;
    }
    return true;
  }
}
