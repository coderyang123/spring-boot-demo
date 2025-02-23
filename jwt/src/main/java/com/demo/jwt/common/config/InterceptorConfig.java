package com.demo.jwt.common.config;

import com.demo.jwt.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author yueyang
 * @since 2021-08-03 21:15:00
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new JwtInterceptor())
        // 拦截的路径
        .addPathPatterns("/**")

        // 排除登录接口
        .excludePathPatterns("/jwt/getToken", "/jwt/needlessToken");
  }
}
