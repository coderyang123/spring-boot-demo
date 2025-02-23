package com.demo.springsecurity.common.handler;

import com.alibaba.fastjson.JSON;
import com.demo.springsecurity.common.enums.ErrorCodeEnum;
import com.demo.springsecurity.domain.common.ResponseResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * 安全认证权限不足处理器
 *
 * @author yueyang
 * @since 2021-04-28 23:44:00
 */
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AccessDeniedException e)
      throws IOException {
    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
    httpServletResponse.setContentType("application/json;charset=utf-8");
    PrintWriter out = httpServletResponse.getWriter();
    out.write(JSON.toJSONString(ResponseResult.failure(ErrorCodeEnum.USER_NO_PERMISSION)));
    out.flush();
    out.close();
  }
}
