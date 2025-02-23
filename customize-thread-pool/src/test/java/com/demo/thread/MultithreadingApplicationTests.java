package com.demo.thread;

import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultithreadingApplicationTests {

  @Test
  void getTest() {
    String content = HttpUtil.get("https://www.h4iri.cn/");
    System.out.println(content);
  }
}
