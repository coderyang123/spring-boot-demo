package com.demo.properties2;

import com.demo.properties2.config.yml.TestYml;
import com.demo.properties2.config.yml.TestYml2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YmlTests {
  @Autowired private TestYml testYml;
  @Autowired private TestYml2 testYml2;

  @Test
  void test() {
    String name = testYml.getName();
    int age = testYml.getAge();
    String gender = testYml.getGender();
    String otherProperty = testYml.getOtherProperty();

    System.out.println("name:" + name);
    System.out.println("age:" + age);
    System.out.println("gender:" + gender);
    System.out.println("otherProperty:" + otherProperty);
  }

  @Test
  void test2() {
    String name = testYml2.getName();
    int age = testYml2.getAge();
    String gender = testYml2.getGender();
    String otherProperty = testYml2.getOtherProperty();

    System.out.println("name:" + name);
    System.out.println("age:" + age);
    System.out.println("gender:" + gender);
    System.out.println("otherProperty:" + otherProperty);
  }
}
