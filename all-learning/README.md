# Spring Boot 开发项目规范案例

> 本项目主要基于`Spring Boot`集成`Mybatis-Plus`演示项目开发时的各种规范

## 1 基础框架搭建

### 1.1 主要依赖

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
</dependencies>
```

### 1.2 新建数据库表（以user表为例）

```sql
CREATE TABLE `user`
(
    `id`          bigint unsigned NOT NULL COMMENT '主键',
    `username`    varchar(20)  DEFAULT NULL COMMENT '用户名',
    `password`    varchar(20)  DEFAULT NULL COMMENT '密码',
    `email`       varchar(100) DEFAULT NULL COMMENT '邮箱',
    `age`         int          DEFAULT NULL COMMENT '年龄',
    `phone`       varchar(20)  DEFAULT NULL COMMENT '手机号',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `modify_time` datetime     DEFAULT NULL COMMENT '修改时间',
    `creator`     varchar(100) DEFAULT NULL COMMENT '创建人',
    `operator`    varchar(100) DEFAULT NULL COMMENT '操作人',
    `status`      tinyint      DEFAULT NULL COMMENT '状态 0：未删除 1：已删除',
    `version`     bigint       DEFAULT NULL COMMENT '版本号（用于乐观锁）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

### 1.3 建立对应的实体类

1.3.1 [`BaseEntity.java`](./src/main/java/com/demo/alllearning/domain/entity/BaseEntity.java) 实体类基类  
1.3.2 [`UserDO.java`](./src/main/java/com/demo/alllearning/domain/entity/UserDO.java) 用户实体类  
1.3.3 [`UserDTO.java`](./src/main/java/com/demo/alllearning/domain/dto/UserDTO.java) 用户传输实体类  
1.3.4 [`UserQueryDTO.java`](./src/main/java/com/demo/alllearning/domain/dto/UserQueryDTO.java) 用户数据查询实体  
1.3.5 [`UserVO.java`](./src/main/java/com/demo/alllearning/domain/vo/UserVO.java) 用户视图实体类

## 2 集成Mybatis-Plus

### 2.1 引入依赖

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
        <version>3.3.1</version>
    </dependency>
</dependencies>
```

### 2.2 添加数据库相应配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/all_learning?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: root
```

### 2.3 新建实体类对应Mapper

2.3.1 [`UserMapper.java`](./src/main/java/com/demo/alllearning/mapper/UserMapper.java)  
2.3.2 [`AllLearningApplication.java`](./src/main/java/com/demo/alllearning/AllLearningApplication.java)
启动类上加包扫描注解 `@MapperScan("com.demo.alllearning.mapper")`

### 2.4 实体类添加相应的注解

2.4.1 类上添加`@TableName("user")` 标识表名   
2.4.2 主键ID添加`@TableId(type = IdType.ASSIGN_ID)` 标识主键及主键生成策略  
2.4.3 version字段添加`@Version`用于乐观锁

### 2.5 配置自动填充系统级字段

2.5.1 [`CommonMetaObjectHandler.java`](./src/main/java/com/demo/alllearning/common/handler/CommonMetaObjectHandler.java)  
2.5.2 在实体类字段上添加相应注解：`@TableField(fill = FieldFill.INSERT)`
新增时填充、`@TableField(fill = FieldFill.INSERT_UPDATE)` 新增/更新时填充

### 2.6 配置乐观锁生效和解决查询的Page对象的total字段为0的问题

2.6.1 [`MybatisPlusConfig.java`](./src/main/java/com/demo/alllearning/common/config/MybatisPlusConfig.java)
> 更新时乐观锁使用规则：  
> 1.如果更新数据中version字段为null：更新不使用乐观锁，version字段不累加  
> 2.如果更新数据中version字段不为null，但值和数据库里的不一致：更新使用乐观锁，更新失败  
> 3.如果更新数据中version字段不为null，且值和数据库里的一致：更新使用乐观锁，更新成功

### 2.7 配置逻辑删除

2.7.1 在删除字段上标注`@TableLogic`注解，代表该字段为逻辑删除  
2.7.2 配置逻辑删除和未删除的值，和默认值一致则可以省略：

```yaml
mybatis-plus:
  global-config:
    db-config:
      logic-delete-value: 1 # 逻辑已删除的值，默认1
      logic-not-delete-value: 0 # 逻辑未删除的值，默认0
```

## 3 集成参数校验

### 3.1 引入依赖

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### 3.2 新建分组校验接口

3.2.1 [`InsertValidationGroup.java`](./src/main/java/com/demo/alllearning/common/validate/InsertValidationGroup.java)
新增时校验   
3.2.2 [`UpdateValidationGroup.java`](./src/main/java/com/demo/alllearning/common/validate/UpdateValidationGroup.java)
更新时校验

### 3.3 在用户传输实体类字段上加相应注解

3.3.1 `@NotBlank(message = "用户名不能为空！", groups = {InsertValidationGroup.class}) ` 新增时做非空校验  
3.3.2 `@NotBlank(message = "密码不能为空！", groups = {InsertValidationGroup.class})` 新增时做非空校验  
3.3.3 `@Length(min = 6, max = 18, message = "密码长度6~18位！")` 新增/更新时做长度校验  
3.3.4 `@Max(value = 60, message = "年龄不能大于150岁！")` 新增/更新时做大小校验  
3.3.5 `@Min(value = 18, message = "年龄不能小于18岁！")` 新增/更新时做大小校验  
3.3.6 `@Email(message = "无效邮箱！")` 新增/更新时做邮箱格式校验

### 3.4 在控制层添加注解

3.4.1 在类上加`@Validated`注解开启基础类型的校验  
3.4.2 在接口方法入参也加上`@Validated(UpdateValidationGroup.class)`校验入参合法性  
3.4.3 编写校验工具类 [`ValidatorUtils.class`](./src/main/java/com/demo/alllearning/common/util/ValidatorUtils.java)    
3.4.4 在`service`层使用工具方法校验参数的合法性 `ValidatorUtils.validate(pageQuery);`

## 4 实现统一异常处理

### 4.1 自定义系统业务异常类：[`BusinessException.class`](src/main/java/com/demo/alllearning/common/exception/BusinessException.java)

### 4.2 编写全局异常处理类：[`GlobalExceptionHandler.class`](src/main/java/com/demo/alllearning/common/exception/GlobalExceptionHandler.java)

## 5 集成本地缓存

### 5.1 引入依赖

```xml

<dependencys>
    <dependency>
        <groupId>com.github.ben-manes.caffeine</groupId>
        <artifactId>caffeine</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
    </dependency>
</dependencys>
```

### 5.2 编写缓存配置类：[`CaffeineCacheConfig.class`](./src/main/java/com/demo/alllearning/common/config/CaffeineCacheConfig.java)

### 5.3 在控制层接口方法上添加相应注解

5.3.1 `@CacheEvict(cacheNames = USERS_CACHE, allEntries = true)`对数据有修改就清除缓存  
5.3.2 `@CacheEvict(cacheNames = USERS_CACHE, allEntries = true)` 对数据有修改就清除缓存  
5.3.3 `@CacheEvict(cacheNames = USERS_CACHE, allEntries = true)` 对数据有修改就清除缓存  
5.3.4 `@Cacheable(cacheNames = USERS_CACHE)` 查询使用缓存  
