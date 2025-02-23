package com.demo.email.util;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class JasyptUtilsTest {

  @Resource JasyptUtils jasyptUtils;

  @Test
  void generatePassword() {
    jasyptUtils.generatePassword();
  }
}
