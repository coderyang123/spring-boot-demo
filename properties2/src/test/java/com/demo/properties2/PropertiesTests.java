package com.demo.properties2;

import com.demo.properties2.config.properties.TestProperties;
import com.demo.properties2.config.properties.TestProperties2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertiesTests {
  @Value("${test2.user2.name2}")
  public String name;

  @Autowired private TestProperties testProperties;
  @Autowired private TestProperties2 testProperties2;

  @Test
  void test() {
    String name = testProperties.getName();
    int age = testProperties.getAge();
    String gender = testProperties.getGender();
    String otherProperty = testProperties.getOtherProperty();

    System.out.println("name:" + name);
    System.out.println("age:" + age);
    System.out.println("gender:" + gender);
    System.out.println("otherProperty:" + otherProperty);
  }

  @Test
  void test2() {
    System.out.println("name:" + name);
  }

  @Test
  void test3() {
    String name = testProperties2.getName();
    int age = testProperties2.getAge();
    String gender = testProperties2.getGender();
    String otherProperty = testProperties2.getOtherProperty();

    System.out.println("name:" + name);
    System.out.println("age:" + age);
    System.out.println("gender:" + gender);
    System.out.println("otherProperty:" + otherProperty);
  }
}
