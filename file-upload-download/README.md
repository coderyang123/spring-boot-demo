# Spring Boot 演示文件上传案例

> 本项目主要演示如何实现文件上传（单个和分片上传）功能

## 1 [`pom.xml`](./pom.xml)

主要添加的项目依赖

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## 2 [`application.yml`](./src/main/resources/application.yml)

```yaml
spring:
  servlet:
    multipart:
      enabled: true # 开启文件上传
      location: D:/home/uploadPath/ # 文件上传的路径
      max-request-size: 200MB # 上传的总文件大小
      max-file-size: 50MB # 上传的单个文件大小
```

## 3 [`FileController.java`](./src/main/java/com/demo/fileupload/controller/FileController.java)

## 4 文件、目录创建重命名删除方法

[`FileUtils.java`](./src/main/java/com/demo/fileupload/util/FileUtils.java)

## 5 `transferTo(java.io.File)`方法解析

全类名：`org.springframework.web.multipart.MultipartFile#transferTo(java.io.File)`  
调用链：`MultipartFile#transferTo() -> StandardMultipartHttpServletRequest#transferTo() -> ApplicationPart#write() ->
DiskFileItem#write() -> IOUtils.copy() -> IOUtils.copyLarge()`  
在`IOUtils.copyLarge()`方法里默认的buffer为`4096KB`大小