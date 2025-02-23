package com.demo.properties2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XmlTests {

  @Value("${druid.driverClassName}")
  private String driverClassName;

  @Value("${druid.url}")
  private String url;

  @Value("${druid.username}")
  private String username;

  @Value("${druid.password}")
  private String password;

  @Test
  void test() {
    System.out.println("driverClassName:" + driverClassName);
    System.out.println("url:" + url);
    System.out.println("username:" + username);
    System.out.println("password:" + password);
  }
}
