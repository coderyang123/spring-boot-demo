# Spring Boot实现异步任务

> 此 demo 主要演示了 Spring Boot 如何使用 Spring 原生提供的异步任务支持，实现异步执行任务。

## 1 [`application.yml`](./src/main/resources/application.yml)

```yaml
spring:
  task:
    execution:
      pool:
        # 最大线程数
        max-size: 16
        # 核心线程数
        core-size: 16
        # 存活时间
        keep-alive: 10s
        # 队列大小
        queue-capacity: 100
        # 是否允许核心线程超时
        allow-core-thread-timeout: true
      # 线程名称前缀
      thread-name-prefix: async-task-
```

## 2 [`AsyncApplication.java`](./src/main/java/com/demo/async/AsyncApplication.java)

启动类添加`@EnableAsync`注解开启异步任务

```java

@SpringBootApplication
@EnableAsync
public class AsyncApplication {

  public static void main(String[] args) {
    SpringApplication.run(AsyncApplication.class, args);
  }
}
```

## 3 [`TaskFactory.java`](./src/main/java/com/demo/async/task/TaskFactory.java)

```java
/** @Description: 异步任务类 @Author: yueyang @CreateTime 2021-01-31 23:09:00 */
@Component
public class TaskFactory {
  /** 模拟5秒的异步任务-有返回值 */
  @Async
  public Future<Boolean> asyncTask1() throws InterruptedException {
    doTask("asyncTask1", 5);
    return new AsyncResult<>(Boolean.TRUE);
  }

  /** 模拟2秒的异步任务-有返回值 */
  @Async
  public Future<Boolean> asyncTask2() throws InterruptedException {
    doTask("asyncTask2", 2);
    return new AsyncResult<>(Boolean.TRUE);
  }

  /** 模拟3秒的异步任务-有返回值 */
  @Async
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
```

## 4 [`AsyncApplicationTests.java`](./src/test/java/com/demo/async/AsyncApplicationTests.java)

```java

@SpringBootTest
class AsyncApplicationTests {

  @Autowired
  private TaskFactory task;

  @Test
  void contextLoads() {
  }

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
```

## 5 运行结果

### test1()

```bash
2021-01-31 23:29:52.935  INFO 10684 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
异步任务全部执行结束，总耗时：32毫秒
```

### test2()

```bash
2021-01-31 23:30:27.434  INFO 9876 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
asyncTask1开始执行，当前线程名称【async-task-1】
asyncTask3开始执行，当前线程名称【async-task-3】
asyncTask2开始执行，当前线程名称【async-task-2】
asyncTask2执行成功，当前线程名称【async-task-2】
asyncTask3执行成功，当前线程名称【async-task-3】
asyncTask1执行成功，当前线程名称【async-task-1】
异步任务全部执行结束，总耗时：5038毫秒
```

### test3()

```bash
2021-01-31 23:31:12.590  INFO 17880 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
无返回值的异步任务全部执行结束，总耗时：26毫秒
```

## 6 参考

- Spring Boot 异步任务线程池的配置
  参考官方文档：https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#boot-features-task-execution-scheduling
