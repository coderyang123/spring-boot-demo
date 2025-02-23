# Spring AOP 应用于日志的案例

> 利用 Spring AOP 模块自带的自定义追踪拦截器实现

# 1 基础框架搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
</dependencies>
```

## 1.2 新建AOP切面类

[`AopLog.java`](src/main/java/com/demo/aop2/aspect/AopLog.java) AOP切面类

## 1.3 建立测试Controller

[`TestController.java`](src/main/java/com/demo/aop2/controller/TestController.java) 测试Controller

## 1.4 配置日志输出等级

[`TestController.java`](src/main/resources/application.yml) 测试Controller