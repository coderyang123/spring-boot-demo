package com.demo.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者示例
 *
 * @author yueyang
 * @since 2022-04-28 09:37:00
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaProducer {
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @GetMapping("/msg/{message}")
  public void sendMessage(@PathVariable("message") String normalMessage) {
    log.info("message received : " + normalMessage);
    kafkaTemplate.send("messages", normalMessage);
    log.info("Kafka message [" + normalMessage + "] send success!");
  }
}
