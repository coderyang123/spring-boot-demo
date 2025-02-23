package com.demo.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 设定任务
 *
 * @author yueyang
 * @since 2022-04-12 16:50:00
 */
@SpringBootApplication

// 开启定时任务功能
@EnableScheduling
public class TaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskApplication.class, args);
  }
}
