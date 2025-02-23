package com.demo.mybatisplus.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import java.util.Map;
import java.util.Objects;
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

    // 自动分页配置
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

    // 乐观锁配置
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

    // 动态表名配置
    DynamicTableNameInnerInterceptor innerInterceptor = new DynamicTableNameInnerInterceptor();
    innerInterceptor.setTableNameHandler(
        (sql, tableName) -> {
          // 获取要查询的表后缀
          Map<String, String> paramMap = RequestDataHelper.getRequestData();
          if (Objects.isNull(paramMap)) {
            return tableName;
          }
          return tableName + paramMap.get("tableSuffix");
        });
    RequestDataHelper.removeRequestData();
    interceptor.addInnerInterceptor(innerInterceptor);

    return interceptor;
  }
}
