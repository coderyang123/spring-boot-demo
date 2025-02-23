package com.demo.springsecurity.common.handler;

import com.alibaba.fastjson.JSON;
import com.demo.springsecurity.common.enums.ErrorCodeEnum;
import com.demo.springsecurity.domain.common.ResponseResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 安全认证失败处理器
 *
 * @author yueyang
 * @since 2021-04-26 22:31:00
 */
@Component
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AuthenticationException e)
      throws IOException {
    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    httpServletResponse.setContentType("application/json;charset=utf-8");
    PrintWriter out = httpServletResponse.getWriter();
    out.write(JSON.toJSONString(ResponseResult.failure(ErrorCodeEnum.USER_LOGIN_ERROR)));
    out.flush();
    out.close();
  }
}
