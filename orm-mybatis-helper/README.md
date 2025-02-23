# Mybatis ORM 框架集成通用Mapper和分页助手案例

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

    <!-- 通用Mapper -->
    <dependency>
        <groupId>tk.mybatis</groupId>
        <artifactId>mapper-spring-boot-starter</artifactId>
        <version>2.1.5</version>
    </dependency>

    <!-- 分页助手 -->
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper-spring-boot-starter</artifactId>
        <version>1.4.1</version>
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

[`mybatis.sql`](./src/main/resources/sql/mybatis.sql)

## 1.3 建立对应的实体类

[`UserDO.java`](./src/main/java/com/demo/mybatis/entity/UserDO.java) 用户实体类

## 1.4 建立对应的映射类

[`UserMapper.java`](./src/main/java/com/demo/mybatis/mapper/UserMapper.java) 用户管理映射类

## 1.5 配置包扫描路径

[`OrmMybatisHelperApplication.java`](./src/main/java/com/demo/mybatis/OrmMybatisHelperApplication.java)

## 1.6 配置数据库连接信息

[`application.yml`](./src/main/resources/application.yml)

# 2 基本CRUD示例

[`UserMapperTest.java`](./src/test/java/com/demo/mybatis/UserMapperTest.java)