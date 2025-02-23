package com.demo.async;

import com.demo.async.task.TaskFactory;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AsyncApplicationTests {

  @Autowired private TaskFactory task;

  @Test
  void contextLoads() {}

  /** 测试异步任务，只调用但不手动阻塞，看不到异步方法的输出 */
  @Test
  public void test1() throws InterruptedException {
    long start = System.currentTimeMillis();
    Future<Boolean> asyncTask1 = task.asyncTask1();
    Future<Boolean> asyncTask2 = task.asyncTask2();
    Future<Boolean> asyncTask3 = task.asyncTask3();
    long end = System.currentTimeMillis();
    System.out.printf("异步任务全部执行结束，总耗时：%d毫秒", (end - start));
  }

  /** 测试异步任务，调用且手动阻塞，可以看到异步方法的输出 */
  @Test
  public void test2() throws InterruptedException, ExecutionException {
    long start = System.currentTimeMillis();
    Future<Boolean> asyncTask1 = task.asyncTask1();
    Future<Boolean> asyncTask2 = task.asyncTask2();
    Future<Boolean> asyncTask3 = task.asyncTask3();

    // 调用 get() 阻塞主线程
    asyncTask1.get();
    asyncTask2.get();
    asyncTask3.get();
    long end = System.currentTimeMillis();
    System.out.printf("异步任务全部执行结束，总耗时：%d毫秒", (end - start));
  }

  /** 测试无返回值的异步任务，只调用但不手动阻塞，看不到异步方法的输出 */
  @Test
  public void test3() throws InterruptedException {
    long start = System.currentTimeMillis();
    for (int i = 0; i < 10; i++) {
      task.asyncTask3withoutResult();
    }
    long end = System.currentTimeMillis();
    System.out.printf("无返回值的异步任务全部执行结束，总耗时：%d毫秒", (end - start));
  }
}
