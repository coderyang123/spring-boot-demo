package com.demo.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者示例
 *
 * @author yueyang
 * @since 2022-04-28 09:40:00
 */
@Slf4j
@Component
public class KafkaConsumer {

  /**
   * 消费监听
   *
   * @param record record
   */
  @KafkaListener(topics = {"messages"})
  public void receiveMessages(ConsumerRecord<?, ?> record) {
    // 消费的哪个topic、partition的消息,打印出消息内容
    log.info("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
  }
}
