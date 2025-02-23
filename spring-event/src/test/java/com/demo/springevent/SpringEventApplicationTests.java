package com.demo.springevent;

import com.demo.springevent.config.DemoPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringEventApplicationTests {
  @Autowired private DemoPublisher demoPublisher;

  @Test
  void contextLoads() {}

  @Test
  void publishTest() {
    demoPublisher.publish("hello");
  }
}
