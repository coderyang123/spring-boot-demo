package com.demo.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class LogLog4j2ApplicationTests {

  @Test
  void contextLoads() {
    log.debug("debug hello world");
    log.info("info hello world");
    log.warn("warn hello world");
    log.error("error hello world");
  }
}
