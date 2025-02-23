package com.demo.springsecurity.common.handler;

import com.alibaba.fastjson.JSON;
import com.demo.springsecurity.domain.common.ResponseResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 安全认证成功处理器
 *
 * @author yueyang
 * @since 2021-04-26 22:31:00
 */
@Component
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      Authentication authentication)
      throws IOException {
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    httpServletResponse.setContentType("application/json;charset=utf-8");
    PrintWriter out = httpServletResponse.getWriter();
    out.write(JSON.toJSONString(ResponseResult.success()));
    out.flush();
    out.close();
  }
}
