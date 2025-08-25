package com.demo.rest.template.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置类
 *
 * @author yueyang
 * @since 2025-08-25 22:50:00
 */
@Configuration
public class RestTemplateConfig {
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
        // 设置连接超时时间为5秒
        .setConnectTimeout(Duration.ofSeconds(5))
        // 设置读取超时时间为5秒
        .setReadTimeout(Duration.ofSeconds(5))
        .build();
  }
}
