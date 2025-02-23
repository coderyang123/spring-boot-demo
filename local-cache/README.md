# 使用ConcurrentHashMap实现本地缓存案例

# 1.主要依赖

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
</dependencies>
```

# 2.项目结构

## 2.1 实体类

[Cache](src/main/java/com/demo/cache/entity/Cache.java)

## 2.2 自定义缓存线程工厂类

[CacheThreadFactory](src/main/java/com/demo/cache/config/CacheThreadFactory.java)

## 2.3 缓存工具类

[LocalCacheUtils](src/main/java/com/demo/cache/utils/LocalCacheUtils.java)

## 2.4 清理过期缓存线程类

[CleanTimeOutThread](src/main/java/com/demo/cache/entity/CleanTimeOutThread.java)

## 2.5 控制类

[Controller](src/main/java/com/demo/cache/controller/Controller.java)