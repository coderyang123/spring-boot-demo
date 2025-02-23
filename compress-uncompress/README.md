# 图片和文件压缩解压缩案例

> 本项目主要是图片压缩和文件压缩解压缩的示例

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

    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
    </dependency>

    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-core</artifactId>
        <version>5.7.18</version>
    </dependency>
</dependencies>
```

### 1.2 工具类

1.2.1 [`FileUtil.java`](./src/main/java/com/demo/compressuncompress/utils/FileUtil.java) 文件相关工具类  
1.2.2 [`ImageUtil.java`](./src/main/java/com/demo/compressuncompress/utils/ImageUtil.java) 图片相关工具类

## 2 压缩解压缩示例

### 2.1 图片压缩示例

[`FileUtil.java`](./src/main/java/com/demo/compressuncompress/controller/PictureController.java)

### 2.2 文件压缩示例

[`FileController.java`](./src/main/java/com/demo/compressuncompress/controller/FileController.java)