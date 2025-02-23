# Spring Boot 集成 Mybatis-Plus ORM 框架案例

# 1 基础框架搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.5.1</version>
    </dependency>
</dependencies>
```

## 1.2 新建数据库表

[`mybatis_plus.sql`](src/main/resources/sql/mybatis-plus.sql)

## 1.3 建立对应的实体类

[`UserDO.java`](src/main/java/com/demo/mybatisplus/domain/entity/UserDO.java) 用户实体类

## 1.4 建立对应的映射类

[`UserMapper.java`](src/main/java/com/demo/mybatisplus/mapper/UserMapper.java) 用户管理映射类

## 1.5 配置包扫描路径

[`OrmMybatisPlusApplication.java`](src/main/java/com/demo/mybatisplus/OrmMybatisPlusApplication.java)

## 1.6 配置数据库连接信息

[`application.yml`](src/main/resources/application.yml)

## 1.7 全局配置

### 1.7.1 日期时间格式化配置

[`DateTimeFormatConfig.java`](src/main/java/com/demo/mybatisplus/common/config/DateTimeFormatConfig.java)

### 1.7.2 MybatisPlus配置

[`MybatisPlusConfig.java`](src/main/java/com/demo/mybatisplus/common/config/MybatisPlusConfig.java)

### 1.7.3 请求参数传递辅助配置

[`RequestDataHelper.java`](src/main/java/com/demo/mybatisplus/common/config/RequestDataHelper.java)

# 2.基本CRUD示例

[`UserMapperTest.java`](src/test/java/com/demo/mybatisplus/mapper/UserMapperTest.java)

# 3.通用 Service

## 3.1 建立实体类对应 Service 和其实现类

[`UserService.java`](src/main/java/com/demo/mybatisplus/service/UserService.java) 用户管理服务类  
[`UserServiceImpl.java`](src/main/java/com/demo/mybatisplus/service/impl/UserServiceImpl.java) 用户管理服务实现类

## 3.2 mapper通用查询示例

[`UserMapperTest.java`](src/test/java/com/demo/mybatisplus/mapper/UserMapperTest.java)

## 3.3 service通用查询示例

[`UserServiceImplTest.java`](src/test/java/com/demo/mybatisplus/service/impl/UserServiceImplTest.java)

# 4.条件构造器

[`UserServiceImpl2Test.java`](src/test/java/com/demo/mybatisplus/service/impl/UserServiceImpl2Test.java)





