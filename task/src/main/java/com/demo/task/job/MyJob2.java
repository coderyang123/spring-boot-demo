package com.demo.task.job;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * 设定任务-实现接口方式实现
 *
 * @author yueyang
 * @since 2022-08-31 10:50:00
 */
@Slf4j
@Component
public class MyJob2 implements SchedulingConfigurer {

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    // 设定延时执行任务
    taskRegistrar.addFixedRateTask(
        () ->
            log.info(
                "执行定时任1，LOCAL_TIME -> " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)),
        1000);

    // 设定周期执行任务
    TriggerTask triggerTask =
        new TriggerTask(
            // 任务内容
            () ->
                log.info(
                    "执行定时任2，LOCAL_TIME -> "
                        + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)),
            // 设置触发器，传入的TriggerContext类型，返回的是Date类型
            triggerContext -> {
              // 返回执行周期(Date)
              return new CronTrigger("*/2 * * * * ?").nextExecutionTime(triggerContext);
            });
    taskRegistrar.addTriggerTask(triggerTask);

    //    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    //    taskScheduler.setPoolSize(10);
    //    taskScheduler.initialize();
    //    taskRegistrar.setTaskScheduler(taskScheduler);
  }
}
