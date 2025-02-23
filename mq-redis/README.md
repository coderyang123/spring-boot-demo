# Redis发布订阅模式操作示例

# 1.基本框架搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>

    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-pool2</artifactId>
    </dependency>
</dependencies>
```

## 1.2 配置类

[RedisConfig.java](src/main/java/com/demo/redis/config/RedisConfig.java)

## 1.3 消费监听类

[InfoListener.java](src/main/java/com/demo/redis/listener/InfoListener.java)

## 1.4 配置文件

[application.yml](src/main/resources/application.yml)

# 2.发布消息测试

[InfoListenerTest.java](src/test/java/com/demo/redis/listener/InfoListenerTest.java)