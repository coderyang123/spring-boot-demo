package com.demo.properties2.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties配置类2
 *
 * @author yueyang
 * @since 2022-01-07 14:40:00
 */
@Configuration
// 读取配置文件（指定文件绝对路径）
@PropertySource("file:/Users/yueyang/Desktop/config2.properties")
// 读取wxpay节点
@ConfigurationProperties(prefix = "test2.user2")
// 使用set方法将wxpay节点中的值填充到当前类的属性中
@Data
public class TestProperties2 {

  /** 姓名 */
  private String name;

  /** 年龄 */
  private Integer age;

  /** 性别 */
  private String gender;

  /** 其他属性 */
  private String otherProperty;
}
