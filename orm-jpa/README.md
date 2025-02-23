# Spring Boot 集成 JPA ORM 框架案例

# 1 基础框架搭建

## 1.1 主要依赖

```xml

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
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
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>

  <!-- Fastjson -->
  <dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.79</version>
  </dependency>
</dependencies>
```

## 1.2 新建数据库表

[`jpa.sql`](./src/main/resources/sql/jpa.sql)

## 1.3 建立对应的实体类

[`UserDO.java`](./src/main/java/com/demo/jpa/entity/UserDO.java) 用户实体类
[`DepartmentDO.java`](./src/main/java/com/demo/jpa/entity/DepartmentDO.java) 部门实体类

## 1.4 建立对应的映射类

[`UserMapper.java`](./src/main/java/com/demo/jpa/repository/UserDao.java) 用户管理映射类
[`DepartmentDao.java`](./src/main/java/com/demo/jpa/repository/DepartmentDao.java) 用户管理映射类

## 1.5 JPA配置类

[`JpaConfig.java`](./src/main/java/com/demo/jpa/config/JpaConfig.java)

## 1.6 配置数据库连接信息

[`application.yml`](./src/main/resources/application.yml)

# 2 基本CRUD示例

[`UserDODaoTests.java`](./src/test/java/com/demo/jpa/repository/UserDODaoTests.java)

# 3 复杂查询示例

[`DepartmentDaoTests.java`](./src/test/java/com/demo/jpa/repository/DepartmentDaoTests.java)