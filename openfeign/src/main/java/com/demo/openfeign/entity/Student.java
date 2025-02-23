package com.demo.openfeign.entity;

/**
 * 学生类
 *
 * @author yueyang
 * @since 2021-02-18 23:36:00
 */
public class Student {

  private String id;
  private String name;

  public Student() {}

  public Student(String id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Student{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
