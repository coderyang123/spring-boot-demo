package com.demo.springevent.config;

import org.springframework.context.ApplicationEvent;

/**
 * 定义事件
 *
 * @author yueyang
 * @since 2022-02-18 20:03:00
 */
public class DemoEvent extends ApplicationEvent {
  private static final long serialVersionUID = -3840962379732909573L;
  private final String message;

  public DemoEvent(Object source, String message) {
    super(source);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
