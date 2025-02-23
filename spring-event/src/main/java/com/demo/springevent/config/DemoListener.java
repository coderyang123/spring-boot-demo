package com.demo.springevent.config;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 定义事件监听者
 *
 * @author yueyang
 * @since 2022-02-18 20:05:00
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

  /**
   * 使用onApplicationEvent接收消息
   *
   * @param event 事件
   */
  @Override
  public void onApplicationEvent(DemoEvent event) {
    String msg = event.getMessage();
    System.out.println("接收到的信息是：" + msg);
  }
}
