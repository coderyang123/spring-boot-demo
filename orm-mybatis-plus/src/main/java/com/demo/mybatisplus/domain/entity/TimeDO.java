package com.demo.mybatisplus.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 时间实体类
 *
 * @author yueyang
 * @since 2023-01-13 23:57:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("time")
public class TimeDO {

  /** 主键ID */
  private Long id;

  /** 测试本地日期时间 */
  private LocalDateTime localDateTime;

  /** 测试本地日期 */
  private LocalDate localDate;

  /** 测试本地时间 */
  private LocalTime localTime;
}
