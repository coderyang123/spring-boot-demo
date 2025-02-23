package com.demo.properties.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

/**
 * 配置类
 *
 * @author yueyang
 * @since 2022-04-07 22:23:00
 */
@Data
@Component
@ConfigurationProperties(prefix = "servers")
public class Test2Properties implements InitializingBean {

  public static Duration TIME;
  public static DataSize SIZE;

  /** 时间 */
  @DurationUnit(ChronoUnit.HOURS)
  private Duration time;

  /** 大小 */
  @DataSizeUnit(DataUnit.MEGABYTES)
  private DataSize size;

  /** 当初始化Bean完成，私有成员变量被赋值后，给常量字段赋值 */
  @Override
  public void afterPropertiesSet() {
    TIME = time;
    SIZE = size;
  }
}
