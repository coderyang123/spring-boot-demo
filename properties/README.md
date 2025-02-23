# Spring Boot 读取静态配置文件常量

> 本项目主要演示如何读取静态配置文件常量

## 1 基础框架搭建

### 1.1 主要依赖

```xml

<dependencys>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
</dependencys>
```

### 1.2 编写对应的配置类

1.2.1 [`TestProperties.java`](./src/main/java/com/demo/properties/config/TestProperties.java) 配置类  
1.2.2 添加相应注解 `@Data`、`@Component`、`@ConfigurationProperties(prefix = "test.user")`  
1.2.3 实现`InitializingBean`接口，当初始化`Bean`完成，私有成员变量被赋值后，给常量字段赋值

### 1.3 编写配置文件

Spring Boot遵循约定大于配置的原则，约定配置文件加载顺序：`bootstrap.yml` > `application.yml` > `application.properties`  
后加载的配置文件内容覆盖先加载的内容，所以最终生效的是 `application.properties`（如果3者同时存在的话）

```yaml
test:
  user:
    name: tom
    age: 18
    gender: male
    other-property: property
```

## 2 测试

### 2.1 编写测试方法读取配置文件常量值 [`PropertiesApplicationTests.java`](./src/test/java/com/demo/properties/PropertiesApplicationTests.java)

## 3 配置文件参数较少的情况

### 3.1 编写配置文件

```yaml
test2:
  user2:
    name2: foo
```

### 3.2 在需要的地方注入常量

```
@Value("${test2.user2.name2}")
public String name;
```

## 4 测试

### 4.1 编写测试方法读取配置文件常量值 [`PropertiesApplicationTests.java`](./src/test/java/com/demo/properties/PropertiesApplicationTests.java)

## 5 新的时间和日期类型

### 5.1 配置类 [`Test2Properties.java`](./src/main/java/com/demo/properties/config/Test2Properties.java)

### 5.2 测试类 [`PropertiesApplication2Tests.java`](./src/test/java/com/demo/properties/PropertiesApplication2Tests.java)


