package com.demo.fileupload.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 时间日期工具类
 *
 * @author yueyang
 * @since 2021-03-26 23:49:00
 */
public class DateUtils {

  /**
   * 获取当前时间秒数（东八区）
   *
   * @return 当前时间秒数
   */
  public static long getNowSecond() {
    return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+08:00"));
  }
}
