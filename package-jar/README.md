# Spring Boot 应用打成`War`包

## 1 主要依赖

```xml

<dependencys>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencys>
```

## 2 修改启动类 [`PackageJarApplication.java`](./src/main/java/com/demo/packagejar/PackageJarApplication.java)

### 2.1 简单编写个测试接口

## 3 部署项目

### 3.1 项目打包上传至`/usr/local/src/software`

### 3.2 启动服务：`java -jar package-jar-0.0.1-SNAPSHOT.jar`

### 3.3 访问接口：`IP:8080/hello`
