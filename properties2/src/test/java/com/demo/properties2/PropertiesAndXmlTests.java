package com.demo.properties2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@SpringBootTest
class PropertiesAndXmlTests {

  /**
   * 解析XML文件
   *
   * @throws IOException IO异常
   */
  @Test
  void test() throws IOException {
    ClassPathResource resource = new ClassPathResource("my.xml");
    Properties properties = new Properties();
    properties.loadFromXML(resource.getInputStream());

    System.out.println(properties);
  }

  /**
   * 解析XML文件2
   *
   * @throws IOException IO异常
   */
  @Test
  void test2() throws IOException {
    ClassPathResource resource = new ClassPathResource("my.xml");
    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
    properties.loadFromXML(resource.getInputStream());

    System.out.println(properties);
  }

  /**
   * 解析Properties文件
   *
   * @throws IOException IO异常
   */
  @Test
  void test3() throws IOException {
    ClassPathResource resource = new ClassPathResource("my.properties");
    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
    properties.load(resource.getInputStream());

    System.out.println(properties);
  }

  /**
   * 解析Properties文件2，解决中文乱码
   *
   * @throws IOException IO异常
   */
  @Test
  void test4() throws IOException {
    ClassPathResource resource = new ClassPathResource("my.properties");
    Properties properties = PropertiesLoaderUtils.loadProperties(resource);

    // 解决中文乱码问题使用Reader
    Reader reader =
        new BufferedReader(
            new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
    properties.load(reader);

    System.out.println(properties);
  }
}
