package com.demo.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.demo.properties.config.Test2Properties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertiesApplication2Tests {

  @Test
  void test() {
    assertEquals(1, Test2Properties.TIME.toHours());
    assertEquals(10, Test2Properties.SIZE.toMegabytes());
  }
}
