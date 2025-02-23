package com.demo.quartz.config;

import com.demo.quartz.job.PrintTimeJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务配置类
 *
 * @author yueyang
 * @since 2022-08-15 17:43:00
 */
@Configuration
public class QuartzConfig {

  @Bean
  public JobDetail printTimeJobDetail() {
    // 绑定具体的工作任务
    return JobBuilder.newJob(PrintTimeJob.class).storeDurably().build();
  }

  @Bean
  public Trigger printTimeTrigger() {
    // 每5秒执行一次
    CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");

    // 设定任务执行时间
    return TriggerBuilder.newTrigger()
        .forJob(printTimeJobDetail())
        .withSchedule(cronSchedule)
        .build();
  }
}
