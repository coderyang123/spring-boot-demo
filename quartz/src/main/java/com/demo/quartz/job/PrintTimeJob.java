package com.demo.quartz.job;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 自定义任务
 *
 * @author yueyang
 * @since 2022-08-15 17:41:00
 */
@Slf4j
public class PrintTimeJob extends QuartzJobBean {

  @Override
  protected void executeInternal(JobExecutionContext context) {
    log.info(Thread.currentThread().getName() + " :printTime task run...");
    log.info(" LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
  }
}
