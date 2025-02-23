package com.demo.interceptor.config;

import com.demo.interceptor.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author yueyang
 * @since 2021-08-04 21:48:00
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new DemoInterceptor())

        // 拦截所有的路径
        .addPathPatterns("/**")

        // 放行的路径，可添加多个
        .excludePathPatterns("/noAuthentication", "/noAuthentication2");
  }
}
