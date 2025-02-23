# Spring Boot 自定义线程示例

# 1.自定义线程池示例 [`CustomizeThreadPool.java`](src/main/java/com/demo/thread/customizethreadpool/CustomizeThreadPool.java)

> 阿里巴巴手册不推荐以上四种创建线程池的方式，弊端如下：  
> 1.FixedThreadPool和SingleThreadPool: 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。  
> 2.CachedThreadPool和ScheduledThreadPool: 允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。

# 2.ThreadPoolExecutor（推荐）

> 自定义各种参数的自定义线程池

## 2.1 添加依赖

```xml

<dependencys>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
</dependencys>
```

## 2.2 编写配置文件 [`application.yml`](src/main/resources/application.yml)

```yaml
thread-pool:
  core-pool-size: 3
  maximum-pool-size: 6
  keep-alive-time: 10
```

## 2.3 编写参数类 [`ThreadPoolProperties.java`](src/main/java/com/demo/thread/config/ThreadPoolProperties.java)

## 2.4 编写线程池配置类 [`ThreadPoolConfig.java`](src/main/java/com/demo/thread/config/ThreadPoolConfig.java)

## 2.5 另一种方式的配置类 [`ThreadPoolConfig2.java`](src/main/java/com/demo/thread/config/ThreadPoolConfig2.java)

## 2.6 在需要的地方注入线程池

```
@Autowired
private ThreadPoolExecutor threadPoolExecutor;
```