package com.demo.interceptor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 *
 * @author yueyang
 * @since 2021-08-04 21:46:00
 */
public class DemoInterceptor implements HandlerInterceptor {

  /**
   * 此方法会在进入controller之前执行，返回Boolean值决定是否执行后续操作
   *
   * @param request request
   * @param response response
   * @param handler handler
   * @return 是否执行
   */
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    System.out.println("进入拦截器了");
    // 中间写逻辑代码，比如判断是否登录成功，失败则返回false
    // 中间写逻辑代码，比如判断请求头是否携带类token，request.getHeader("token")
    return true;
  }

  /**
   * 此方法将在controller执行之后执行，但是视图还没有解析，可向ModelAndView中添加数据(前后端不分离的)
   *
   * @param request request
   * @param response response
   * @param handler handler
   * @param modelAndView modelAndView
   */
  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) {
    // 向ModelAndView中添加数据（前后端不分离项目）
    System.out.println("controller 执行完了");
  }

  /**
   * 该方法会在整个请求结束（请求结束，但是并未返回结果给客户端）之后执行， 可获取响应数据及异常信息
   *
   * @param request request
   * @param response response
   * @param handler handler
   * @param ex ex
   */
  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    System.out.println("返回的状态码：" + response.getStatus());
    System.out.println("返回的结果：" + response);
    System.out.println("请求结束了");
  }
}
