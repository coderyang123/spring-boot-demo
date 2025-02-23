package com.demo.thread.customizethreadpool;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author yueyang
 * @since 2021-04-11 22:53:00
 */
public class CustomizeThreadPool {

  public static void main(String[] args) {
    // 核心线程数
    int corePoolSize = 3;

    // 最大线程数
    int maximumPoolSize = 6;

    // 超过 corePoolSize 线程数量的线程最大空闲时间
    long keepAliveTime = 2;

    // 创建大小为2的工作队列，用于存放提交的等待执行任务
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1000);

    ThreadPoolExecutor threadPoolExecutor = null;
    try {
      // 创建线程池
      threadPoolExecutor =
          new ThreadPoolExecutor(
              corePoolSize,
              maximumPoolSize,
              keepAliveTime,
              TimeUnit.SECONDS,
              workQueue,
              new ThreadPoolExecutor.AbortPolicy());

      // 循环提交任务
      for (int i = 0; i < 8; i++) {
        final int index = (i + 1);
        threadPoolExecutor.submit(
            () -> {
              System.out.println("我是线程：" + index);
              System.out.println("线程名：" + Thread.currentThread().getName());
            });

        // 每个任务提交后休眠500ms再提交下一个任务，用于保证提交顺序
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      if (Objects.nonNull(threadPoolExecutor)) {
        threadPoolExecutor.shutdown();
      }
    }
  }
}
