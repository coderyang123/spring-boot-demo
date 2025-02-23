package com.demo.alllearning.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus配置
 *
 * @author yueyang
 * @since 2021-03-08 22:48:00
 */
@Configuration
public class MybatisPlusConfig {

  @Bean
  public void configureGlobalSettings() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

    // 开启乐观锁配置
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

    // 处解决查询时有数据但Page对象total为0的问题
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
  }
}
