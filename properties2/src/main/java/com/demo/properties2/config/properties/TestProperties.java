package com.demo.properties2.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties配置类
 *
 * @author yueyang
 * @since 2022-01-07 14:40:00
 */
@Configuration
// 读取配置文件（位于项目resources目录下）
@PropertySource("classpath:config.properties")
// 读取配置文件节点
@ConfigurationProperties(prefix = "test.user")
// 使用set方法将wxpay节点中的值填充到当前类的属性中
@Data
public class TestProperties {

  /** 姓名 */
  private String name;

  /** 年龄 */
  private Integer age;

  /** 性别 */
  private String gender;

  /** 其他属性 */
  private String otherProperty;
}
