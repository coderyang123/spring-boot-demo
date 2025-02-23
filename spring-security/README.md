# Spring Boot 集成 SpringSecurity 演示案例

> 本项目主要基于`Spring Boot`集成`SpringSecurity`实现项目的认证和授权

## 1 技术栈

- SpringSecurity
- MyBatis-Plus
- Fastjson
- thymeleaf

## 2 基础框架搭建

### 2.1 主要依赖

```xml

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>

  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>

  <dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.76</version>
  </dependency>
</dependencies>
```

### 2.2 新建数据库表

2.2.1 [`user.sql`](./src/main/resources/sql/security_db.sql) SQL文件

### 2.3 建立对应的实体类及其他传输类

1.3.1 [`ResponseResult.java`](./src/main/java/com/demo/springsecurity/domain/common/ResponseResult.java) 统一响应体类   
1.3.2 [`UserDTO.java`](./src/main/java/com/demo/springsecurity/domain/dto/UserDTO.java) 用户传输实体类    
1.3.3 [`UserDO.java`](./src/main/java/com/demo/springsecurity/domain/entity/UserDO.java) 用户实体类  
1.3.4 [`ErrorCodeEnum.java`](./src/main/java/com/demo/springsecurity/common/enums/ErrorCodeEnum.java) 错误编码枚举

## 3 集成Mybatis-Plus

### 3.1 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
  </dependency>

  <dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.2</version>
  </dependency>
</dependencies>
```

### 3.2 添加数据库相应配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://:3306/security_db?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: root
```

### 3.3 新建实体类对应Mapper

3.3.1 [`UserMapper.java`](./src/main/java/com/demo/springsecurity/mapper/UserMapper.java)  
3.3.2 [`SpringSecurityApplication.java`](./src/main/java/com/demo/springsecurity/SpringSecurityApplication.java)
启动类上加包扫描注解 `@MapperScan("com.demo.springsecurity.mapper")`

### 3.4 实体类添加相应的注解

3.4.1 类上添加`@TableName("user")` 标识表名   
3.4.2 主键ID添加`@TableId(type = IdType.ASSIGN_ID)` 标识主键及主键生成策略

## 4 配置SpringSecurity

### 4.1 配置认证成功/失败处理器

4.1.1 [`SecurityAccessDeniedHandler.java`](./src/main/java/com/demo/springsecurity/common/handler/SecurityAccessDeniedHandler.java)
无权限访问处理器  
4.1.1 [`SecurityAuthenticationSuccessHandler.java`](./src/main/java/com/demo/springsecurity/common/handler/SecurityAuthenticationSuccessHandler.java)
安全认证成功处理器  
4.1.1 [`SecurityAuthenticationFailureHandler.java`](./src/main/java/com/demo/springsecurity/common/handler/SecurityAuthenticationFailureHandler.java)
安全认证失败处理器

### 4.2 配置认证用户信息详情类

4.2.1 [`AuthenticationUserDetailsDTO.java`](./src/main/java/com/demo/springsecurity/domain/dto/AuthenticationUserDetailsDTO.java)

### 4.3 配置SpringSecurity配置适配器

4.3.1 [`SecurityConfigurerAdapter.java`](./src/main/java/com/demo/springsecurity/common/adapter/SecurityConfigurerAdapter.java)
SpringSecurity配置适配器

### 4.4 配置启动类

4.4.1 [`SpringSecurityApplication.java`](./src/main/java/com/demo/springsecurity/SpringSecurityApplication.java)
启动类上加`@EnableWebSecurity`注解开启安全认证

## 5 编写后台业务

### 5.1 视图层

5.1.1 [`SystemController.java`](./src/main/java/com/demo/springsecurity/controller/SystemController.java)

### 5.2 业务层

5.2.1 [`UserDetailServiceImpl.java`](./src/main/java/com/demo/springsecurity/service/impl/UserDetailsServiceImpl.java)

### 5.3 数据访问层

5.3.1 [`UserMapper.java`](./src/main/java/com/demo/springsecurity/mapper/UserMapper.java)

## 6 编写前台页面

### 6.1 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
</dependencies>
```

### 6.2 页面

- [`login.html`](./src/main/resources/static/login.html)
- [`add.html`](./src/main/resources/templates/add.html)
- [`index.html`](./src/main/resources/templates/index.html)
- [`menu.html`](./src/main/resources/templates/menu.html)
- [`order.html`](./src/main/resources/templates/order.html)
- [`role.html`](./src/main/resources/templates/role.html)
- [`user.html`](./src/main/resources/templates/user.html)

## 7 验证

访问`http://localhost:8080/login.html` 输入 admin/123456 访问
