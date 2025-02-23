# Spring Boot 集成 Mybatis-Plus ORM 框架使用多数据源案例

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
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
        <version>2.5.0</version>
    </dependency>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.1.0</version>
    </dependency>

    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.8.0</version>
    </dependency>

    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>30.0-jre</version>
    </dependency>
</dependencies>
```

## 1.2 新建数据库表

[`demo.sql`](./src/main/resources/sql/demo.sql)

## 1.3 建立对应的实体类

[`User.java`](./src/main/java/com/demo/multi/datasource/domain/User.java) 用户实体类

## 1.4 建立对应的映射类

[`UserMapper.java`](./src/main/java/com/demo/multi/datasource/mapper/UserMapper.java) 用户管理映射类

## 1.5 配置包扫描路径

[`MultiDatasourceMybatisApplication.java`](./src/main/java/com/demo/multi/datasource/MultiDatasourceMybatisApplication.java)

## 1.7 配置数据库连接信息

[`application.yml`](./src/main/resources/application.yml)

# 2 基本示例

[`UserServiceImplTest.java`](./src/test/java/com/demo/multi/datasource/service/impl/UserServiceImplTest.java)