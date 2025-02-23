package com.demo.springsecurity.common.adapter;

import com.demo.springsecurity.common.handler.SecurityAccessDeniedHandler;
import com.demo.springsecurity.common.handler.SecurityAuthenticationFailureHandler;
import com.demo.springsecurity.common.handler.SecurityAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SpringSecurity配置适配器
 *
 * @author yueyang
 * @since 2021-04-23 12:10:00
 */
@Configuration
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
  private final SecurityAuthenticationSuccessHandler successHandler;
  private final SecurityAuthenticationFailureHandler failureHandler;
  private final SecurityAccessDeniedHandler accessDeniedHandler;
  private final UserDetailsService userDetailsService;

  public SecurityConfigurerAdapter(
      SecurityAuthenticationSuccessHandler successHandler,
      SecurityAuthenticationFailureHandler failureHandler,
      SecurityAccessDeniedHandler accessDeniedHandler,
      UserDetailsService userDetailsService) {
    this.successHandler = successHandler;
    this.failureHandler = failureHandler;
    this.userDetailsService = userDetailsService;
    this.accessDeniedHandler = accessDeniedHandler;
  }

  /** 当前配置为form表单登录认证方式 */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 开启formlogin模式
    http.formLogin()
        // 用户未登录时，访问任何资源跳转到该页面
        .loginPage("/login.html")
        // 登录表单form中action的地址，即处理认证的路径
        .loginProcessingUrl("/login")
        // 表单提交用户名字段，默认是username，一致可不配置
        .usernameParameter("username")
        // 表单提交密码字段，默认是password，一致可不配置
        .passwordParameter("password")
        // 认证成功处理器
        .successHandler(successHandler)
        // 认证失败处理器
        .failureHandler(failureHandler);

    // 配置登录后的权限
    http.authorizeRequests()
        // 配置不做拦截的接口/页面
        .antMatchers("/login", "/login.html")
        // 用户可任意访问
        .permitAll()
        // 配置部分权限可访问的接口
        .antMatchers("/system/order")
        // CREATE或DELETE或UPDATE或RETRIEVE权限可访问
        .hasAnyAuthority("CREATE", "DELETE", "UPDATE", "RETRIEVE")
        // 配置ADMIN角色可访问全部接口
        .antMatchers(
            "/system/user", "/system/role", "/system/menu", "/system/add", "/system/addUser")
        // ADMIN角色可访问
        .hasAnyRole("ADMIN")
        // 配置除以上外的接口全部需要鉴权认证
        .anyRequest()
        // 配置在执行请求要求必须已登录
        .authenticated();

    // 禁用跨站csrf攻击防御, 否则无法成功登录
    http.csrf().disable();

    // 配置无权限异常处理器
    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    // 配置用户登出，无需实现此接口
    http.logout().logoutUrl("/logout");
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 调用DetailsService完成用户身份验证
    auth.userDetailsService(userDetailsService)
        .
        // 设置密码加密方式
        passwordEncoder(getBcryptPasswordEncoder());
  }

  @Bean
  public BCryptPasswordEncoder getBcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
