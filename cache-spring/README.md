# Spring 自带缓存使用案例

# 1.基础框架搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
</dependencies>
```

## 1.2 实体类

[DictDO.java](src/main/java/com/demo/cache/domain/DictDO.java)

## 1.2 业务类

[DictDoService.java](src/main/java/com/demo/cache/service/DictDoService.java)

## 1.3 控制类

[DictController.java](src/main/java/com/demo/cache/controller/DictController.java)

## 1.4 启动类

[CacheSpringApplication.java](src/main/java/com/demo/cache/CacheSpringApplication.java)

# 2.测试

## 2.1 访问`http://localhost:8080/dict?id=2`

> 首次访问没有缓存，后续的访问使用缓存

## 2.2 访问`http://localhost:8080/dict/clearDictDoCache`

> 访问后清空缓存