# 操作 Redis 数据库示例

# 1.环境搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
</dependencies>
```

## 1.2 配置类

[RedisConfig.java](src/main/java/com/demo/nosql/config/RedisConfig.java)

## 1.3 配置文件

[application.yml](src/main/resources/application.yml)

# 2.测试

## 2.1 操作Hash类型的数据示例

[HashRedisTemplateTest.java](src/test/java/com/demo/nosql/HashRedisTemplateTest.java)

## 2.2 操作Key示例

[KeyRedisTemplateTest.java](src/test/java/com/demo/nosql/KeyRedisTemplateTest.java)

## 2.3 操作List类型的数据示例

[ListRedisTemplateTest.java](src/test/java/com/demo/nosql/ListRedisTemplateTest.java)

## 2.4 操作Set类型的数据示例

[SetRedisTemplateTest.java](src/test/java/com/demo/nosql/SetRedisTemplateTest.java)

## 2.5 操作String类型的数据示例

[StringRedisTemplateTest.java](src/test/java/com/demo/nosql/StringRedisTemplateTest.java)

## 2.6 操作Set类型的数据示例

[ZSetRedisTemplateTest.java](src/test/java/com/demo/nosql/ZSetRedisTemplateTest.java)