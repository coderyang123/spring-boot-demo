package com.demo.properties;

import static com.demo.properties.config.TestProperties.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertiesApplicationTests {
  @Value("${test2.user2.name2}")
  public String name;

  /** 导入不存在的配置，设置默认值为空字符串 */
  @Value("${test5.user5.name5:#{''}}")
  public String name5;

  /** 导入不存在的配置，设置默认值为null */
  @Value("${test6.user6.name6:#{null}}")
  public String name6;

  /** 导入不存在的配置，用List接收，设置默认值为null */
  @Value("${test7.user7.name7:#{null}}")
  public List<String> nameList7;

  /** 导入存在的配置（但是不赋值），用List接收，设置默认值为null */
  @Value("${test8.user8.name8:#{null}}")
  public List<String> nameList8;

  /** 导入不存在的配置，用String接收，设置默认值为test */
  @Value("${test9.user9.name9:#{'test'}}")
  public String nameList9;

  /** 导入存在的配置（同时赋值），用List接收，设置默认值为null */
  @Value("${test10.user10.name10:#{null}}")
  public List<String> nameList10;

  /** 导入存在的配置（但是不赋值），用String接收，设置默认值为test */
  @Value("${test99.user99.name99:#{'test'}}")
  public String nameList99;

  @Test
  void test1() {
    assertEquals("tom1", NAME);
    assertEquals(1, AGE);
    assertEquals("male1", GENDER);
    assertEquals("property1", OTHER_PROPERTY);
  }

  @Test
  void test2() {
    assertEquals("1", name);
  }

  @Test
  void test3() {
    assertEquals("", name5);
    assertNull(name6);
  }

  @Test
  void test4() {
    assertNull(nameList7);

    // []（空集合）
    assertArrayEquals(new String[] {}, nameList8.toArray());
    assertArrayEquals(new String[] {"foo", "bar", "yang"}, nameList10.toArray());
  }

  @Test
  void test5() {
    assertEquals("test", nameList9);
    assertEquals("", nameList99);
  }
}
