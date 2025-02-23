package com.demo.mybatisplus.common.config;

import java.util.Map;

/**
 * 请求参数传递辅助类
 *
 * @author yueyang
 * @since 2022-07-21 17:58:00
 */
public class RequestDataHelper {
  /** 请求参数存取 */
  private static final ThreadLocal<Map<String, String>> REQUEST_TABLE_NAME = new ThreadLocal<>();

  /**
   * 设置请求表名参数
   *
   * @param requestData 请求参数 MAP 对象
   */
  public static void setRequestData(Map<String, String> requestData) {
    REQUEST_TABLE_NAME.set(requestData);
  }

  /**
   * 获取请求参数
   *
   * @return 请求参数 MAP 对象
   */
  public static Map<String, String> getRequestData() {
    return REQUEST_TABLE_NAME.get();
  }

  /** 移除请求参数 */
  public static void removeRequestData() {
    REQUEST_TABLE_NAME.remove();
  }
}
