package com.demo.docker.job;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 设定任务
 *
 * @author yueyang
 * @since 2022-07-28 16:30:00
 */
@Component
@Slf4j
public class MyJob {

  @Scheduled(cron = "0/3 * * * * ?")
  public void printTime() {
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }
}
