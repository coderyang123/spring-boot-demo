# Spring Boot 开发项目规范案例

> 本项目主要基于`Spring Boot`集成`JWT`演示项目鉴权

## 1 基础框架搭建

### 1.1 主要依赖

```xml

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>

  <dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.18.1</version>
  </dependency>
</dependencies>
```

### 1.2 建立对应的实体类

1.2.1 [`User.java`](src/main/java/com/demo/jwt/domain/User.java) 用户实体类

## 2 编写`JWT`工具类

### 2.1 [`User.java`](src/main/java/com/demo/jwt/util/JwtUtils.java) `JWT`工具类

## 3 新建自定义拦截器类

### 3.1 [`JwtInterceptor.java`](src/main/java/com/demo/jwt/common/interceptor/JwtInterceptor.java) 自定义拦截器

## 4 新建拦截器配置类

### 4.1 [`InterceptorConfig.java`](src/main/java/com/demo/jwt/common/config/InterceptorConfig.java) 拦截器配置

## 3 新建测试接口

### 3.1 [`TestController.java`](src/main/java/com/demo/jwt/controller/TestController.java) 测试类