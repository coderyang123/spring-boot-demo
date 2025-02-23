package com.demo.activemq.controller;

import com.demo.activemq.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
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

  @PostMapping("/id/{id}")
  public void sendMessageId(@PathVariable String id) {
    log.info("开始处理消息发送业务");
    messageService.sendMessageId(id);
    log.info("处理消息发送业务完成");
  }

  @PostMapping("/consumeMessage")
  public void consumeMessage() {
    log.info("开始消费消息业务");
    messageService.consumeMessage();
    log.info("消费消息业务完成");
  }

  @PostMapping("/name")
  public void sendMessageName(@RequestParam("name") String name) {
    log.info("开始处理消息发送业务");
    messageService.sendMessageName(name);
    log.info("处理消息发送业务完成");
  }

  @PostMapping("/data")
  public void sendMessageData(@RequestParam("data") String data) {
    log.info("开始处理消息发送业务");
    messageService.sendMessageData(data);
    log.info("处理消息发送业务完成");
  }
}
