package com.demo.properties2.config.yml;

import com.demo.properties2.factory.YmlPropertySourceFactory;
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
@PropertySource(value = "classpath:config.yml", factory = YmlPropertySourceFactory.class)
// 读取配置文件节点
@ConfigurationProperties(prefix = "test3.user3")
// 使用set方法将wxpay节点中的值填充到当前类的属性中
@Data
public class TestYml {

  /** 姓名 */
  private String name;

  /** 年龄 */
  private Integer age;

  /** 性别 */
  private String gender;

  /** 其他属性 */
  private String otherProperty;
}
