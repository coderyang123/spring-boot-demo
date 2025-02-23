package com.demo.easyexcel.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus配置
 *
 * @author yueyang
 * @since 2022-07-20 12:48:00
 */
@Configuration
public class MybatisPlusConfig {

  /**
   * 配置MybatisPlus拦截器
   *
   * @return MybatisPlus拦截器
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

    // 分页插件配置数据库类型，解决查询时有数据但Page对象total为0的问题
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

    return interceptor;
  }
}
