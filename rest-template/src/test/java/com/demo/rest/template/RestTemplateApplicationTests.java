package com.demo.rest.template;

import com.demo.rest.template.entity.TestResponse;
import com.demo.rest.template.service.TestService;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestTemplateApplicationTests {
  @Resource private TestService testService;

  @Test
  void contextLoads() {
    TestResponse testResponse = testService.postDemo();
    System.out.println(testResponse);
  }
}
