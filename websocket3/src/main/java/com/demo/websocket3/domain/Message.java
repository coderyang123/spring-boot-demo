package com.demo.websocket3.domain;

import lombok.Data;

/**
 * 用户消息实体类
 *
 * @author yueyang
 * @since 2021-06-07 14:37:00
 */
@Data
public class Message {

  /** 用户ID */
  private String userId;

  /** 用户消息 */
  private String message;
}
