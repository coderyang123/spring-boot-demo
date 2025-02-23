# Spring Boot集成redis

> 本项目主要演示如何在项目里面通过`spring-boot-starter-data-redis`来集成`redis`实现缓存系统的参数

## 1 基础框架搭建

### 1.1 主要依赖 [`pom.xml`](./pom.xml)

```xml
<dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
</dependencies>
```

### 1.2 配置文件 [`application.yml`](./src/main/resources/application.yml)

```yaml
spring:
  redis:
    host: 116.62.xxx.97
    port: 6379
    password: xxxxxx
```

### 1.3 测试类 [`RedisApplicationTests.java`](./src/test/java/com/demo/redis/RedisApplicationTests.java)

## 2 存取对象演示

### 2.1 添加依赖 [`pom.xml`](./pom.xml)

```xml
<!-- 缓存连接池-->
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-pool2</artifactId>
</dependency>

<!-- redis 存储 json序列化 -->
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.datatype</groupId>
  <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```

### 2.2 添加配置 [`application.yml`](./src/main/resources/application.yml)

```yaml
spring:
  redis:
    host: 116.62.xxx.97
    port: 6379
    password: xxxxxx
    database: 0
    timeout: 3000ms     #最大等待时间，超时则抛出异常，否则请求一直等待
    lettuce:
      pool:
        max-active: 20  #最大连接数，负值表示没有限制，默认8
        max-wait: -1    #最大阻塞等待时间，负值表示没限制，默认-1
        max-idle: 8     #最大空闲连接，默认8
        min-idle: 0     #最小空闲连接，默认0  
```

### 2.3 添加Redis配置类 [`RedisConfig.java`](./src/main/java/com/demo/redis/config/RedisConfig.java)

### 2.4 测试类 [`RedisTemplateTests.java`](./src/test/java/com/demo/redis/RedisTemplateTests.java)

## 3 使用Redis作为缓存演示

### 3.1 修改Redis配置类 [`RedisConfig.java`](./src/main/java/com/demo/redis/config/RedisConfig.java)

3.1.1 配置类上增加`@EnableCaching`注解

3.1.2 在`RedisTemplate Bbean`里加入:

```java
// 指定要序列化的域，field、get和set，以及修饰符范围，ANY是都有包括private和public
objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
```

3.1.3 新增`KeyGenerator Bean`

3.1.4 新增`CacheManager Bean`

### 3.2 新增Service类模拟数据库查询和修改方法 [`DictDoService.java`](./src/main/java/com/demo/redis/service/DictDoService.java)

### 3.3 测试类 [`RedisCacheTests.java`](./src/test/java/com/demo/redis/RedisCacheTests.java)