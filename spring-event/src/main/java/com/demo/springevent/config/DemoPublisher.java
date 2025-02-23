package com.demo.springevent.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 使用事件发布者发布消息
 *
 * @author yueyang
 * @since 2022-02-18 20:06:00
 */
@Component
public class DemoPublisher {

  private final ApplicationContext applicationContext;

  public DemoPublisher(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public void publish(String message) {
    // 发布事件
    applicationContext.publishEvent(new DemoEvent(this, message));
  }
}
