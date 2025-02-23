# 使用Ehcache缓存案例

# 1.基础框架搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>

    <dependency>
        <groupId>net.sf.ehcache</groupId>
        <artifactId>ehcache</artifactId>
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

[CacheEhcacheApplication.java](src/main/java/com/demo/cache/CacheEhcacheApplication.java)

## 1.5 缓存配置文件

[ehcache.xml](src/main/resources/ehcache.xml)

> 其中自定义配置的缓存name属性要和@Cacheable注解的value属性一一对应

# 2.测试

## 2.1 访问`http://localhost:8080/dict?id=2`

> 首次访问没有缓存，后续的访问使用缓存

## 2.2 访问`http://localhost:8080/dict/clearDictDoCache`

> 访问后清空缓存