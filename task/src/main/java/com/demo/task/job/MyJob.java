package com.demo.task.job;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 设定任务-注解方式实现
 *
 * @author yueyang
 * @since 2022-04-12 16:50:00
 */
@Slf4j
@Component
public class MyJob {

  /** 每月每日每时每分的第0秒触发一次，然后每隔5秒触发一次 */
  @Scheduled(cron = "0/5 * * * * ?")
  public void printTime() {
    log.info(Thread.currentThread().getName() + " :printTime task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 每月每日每时每分的第3秒触发一次，然后每隔5秒触发一次 */
  @Scheduled(cron = "3/5 * * * * ?")
  public void printTime2() {
    log.info(Thread.currentThread().getName() + " :printTime task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 每月每日每时每分的第0和第3秒各触发一次 */
  @Scheduled(cron = "0 * * * * ?")
  @Scheduled(cron = "3 * * * * ?")
  public void printTime3() {
    log.info(Thread.currentThread().getName() + " :printTime3 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 每月每日每时每分的第0和第3秒各触发一次 */
  @Scheduled(cron = "0,3 */1 * * * ?")
  public void printTime4() {
    log.info(Thread.currentThread().getName() + " :printTime4 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 每月每日每隔2小时的第0分钟的第0秒触发一次 */
  @Scheduled(cron = "0 0 */2 * * ?")
  public void printTime5() {
    log.info(Thread.currentThread().getName() + " :printTime5 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 每月每日的第0时的第0分钟的第0秒触发一次 */
  @Scheduled(cron = "0 0 0 * * ?")
  public void printTime6() {
    log.info(Thread.currentThread().getName() + " :printTime5 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 每月每日的第0时的第0分钟的第0秒触发一次 */
  @Scheduled(cron = "0 0 0 ? * *")
  public void printTime7() {
    log.info(Thread.currentThread().getName() + " :printTime7 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /**
   * 每月的第5日0时0分0秒触发一次，如果第5日是星期六，则将在最近的工作日：星期五，即4日触发。如果5日是星期天，则在6日(周一)触发；如果5日在星期一
   * 到星期五中的一天，则就在5日触发。另外一点，W的最近寻找不会跨过月份。
   */
  @Scheduled(cron = "0 0 0 5W * ?")
  public void printTime8() {
    log.info(Thread.currentThread().getName() + " :printTime8 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 每月的每个工作日（周一至周五）的0时0分0秒触发一次 */
  @Scheduled(cron = "0 0 0 ? * 2-6")
  public void printTime9() {
    log.info(Thread.currentThread().getName() + " :printTime9 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 每月的第1天的第0时0分0秒触发一次 */
  @Scheduled(cron = "0 0 0 1 * *")
  public void printTime10() {
    log.info(Thread.currentThread().getName() + " :printTime10 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 项目启动后2s先执行一次，然后每隔2min执行一次此任务 */
  @Scheduled(initialDelay = 1000 * 2, fixedRate = 1000 * 60 * 2)
  public void printTime11() {
    log.info(Thread.currentThread().getName() + " :printTime11 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 项目启动后2s先执行一次，然后上次调用完后隔2min执行一次此任务 */
  @Scheduled(initialDelay = 1000 * 2, fixedDelay = 1000 * 60 * 2)
  public void printTime12() {
    log.info(Thread.currentThread().getName() + " :printTime12 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }

  /** 项目启动后2s先执行一次，然后上次调用完后隔2min执行一次此任务 */
  @Scheduled(
      initialDelayString = "${schedule.initialDelay}",
      fixedDelayString = "${schedule.fixedDelay}")
  public void printTime13() {
    log.info(Thread.currentThread().getName() + " :printTime13 task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }
}
