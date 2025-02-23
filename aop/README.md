# Spring AOP 应用于日志的案例

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

    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.7.20</version>
    </dependency>

    <!-- 解析 UserAgent 信息 -->
    <dependency>
        <groupId>eu.bitwalker</groupId>
        <artifactId>UserAgentUtils</artifactId>
        <version>1.21</version>
    </dependency>

    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>31.0.1-jre</version>
    </dependency>
</dependencies>
```

## 1.2 新建AOP切面类

[`AopLog.java`](./src/main/java/com/demo/aop/aspect/AopLog.java) AOP切面类

## 1.3 建立日志信息实体类

[`Log.java`](./src/main/java/com/demo/aop/entity/Log.java) 日志信息实体类

## 1.4 建立测试Controller

[`TestController.java`](./src/main/java/com/demo/aop/controller/TestController.java) 测试Controller