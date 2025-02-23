package com.demo.rocketmq.controller;

import com.demo.rocketmq.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息管理
 *
 * @author yueyang
 * @since 2022-08-16 10:18:00
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/message")
@RestController
public class MessageController {
  private final MessageService messageService;

  @PostMapping("/sync/id/{id}")
  public void synchronizedSendMessageId(@PathVariable String id) {
    log.info("开始处理消息发送业务");
    messageService.synchronizedSendMessageId(id);
    log.info("处理消息发送业务完成");
  }

  @PostMapping("/async/id/{id}")
  public void asynchronizedSendMessageId(@PathVariable String id) {
    log.info("开始处理消息发送业务");
    messageService.asynchronizedSendMessageId(id);
    log.info("处理消息发送业务完成");
  }
}
