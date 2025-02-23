package com.demo.async.task;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 异步任务类
 *
 * @author yueyang
 * @since 2021-01-31 23:09:00
 */
@Component
public class TaskFactory {

  /** 模拟5秒的异步任务-有返回值 */
  @Async("serviceExecutor")
  // @Async 在yml里配置线程池就不用在注解里指定线程名
  public Future<Boolean> asyncTask1() throws InterruptedException {
    doTask("asyncTask1", 5);
    return new AsyncResult<>(Boolean.TRUE);
  }

  /** 模拟2秒的异步任务-有返回值 */
  @Async("serviceExecutor")
  //  @Async 在yml里配置线程池就不用在注解里指定线程名
  public Future<Boolean> asyncTask2() throws InterruptedException {
    doTask("asyncTask2", 2);
    return new AsyncResult<>(Boolean.TRUE);
  }

  /** 模拟3秒的异步任务-有返回值 */
  @Async("serviceExecutor")
  //  @Async 在yml里配置线程池就不用在注解里指定线程名
  public Future<Boolean> asyncTask3() throws InterruptedException {
    doTask("asyncTask3", 3);
    return new AsyncResult<>(Boolean.TRUE);
  }

  /** 模拟3秒的异步任务-无返回值 */
  @Async
  public void asyncTask3withoutResult() throws InterruptedException {
    doTask("asyncTask3withoutResult", 3);
  }

  private void doTask(String taskName, Integer time) throws InterruptedException {
    System.out.printf("%s开始执行，当前线程名称【%s】\n", taskName, Thread.currentThread().getName());
    TimeUnit.SECONDS.sleep(time);
    System.out.printf("%s执行成功，当前线程名称【%s】\n", taskName, Thread.currentThread().getName());
  }
}
