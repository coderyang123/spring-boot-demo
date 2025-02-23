# Spring Boot实现异步任务

> 此 demo 主要演示了 Spring Boot 如何使用集成 Druid 数据源

## 1 主要依赖

```yaml
<dependencies>
<!--Druid依赖-->
<dependency>
<groupId>com.alibaba</groupId>
<artifactId>druid-spring-boot-starter</artifactId>
<version>1.2.6</version>
</dependency>

<!--配合SQL统计-->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
</dependencies>
```

## 2 [`application.yml`](./src/main/resources/application.yml)

## 3 [`UserController.java`](./src/main/java/com/demo/druid/controller/UserController.java) 几个演示接口

## 4 `SQL`监控地址

`http://127.0.0.1:8080/druid/login.html`

