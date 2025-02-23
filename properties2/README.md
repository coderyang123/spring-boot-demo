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

## 2 读取`Properties`文件

- Spring Boot遵循约定大于配置的原则，约定配置文件加载顺序：内部文件 > 外部文件
- 后加载的配置文件内容覆盖先加载的内容，所以最终生效的是外部文件（如果二者同时存在的话）
- 项目config文件夹下配置文件优先级 > 项目资源文件夹下的config文件夹的配置文件优先级 > 项目资源文件夹下的配置文件优先级

### 2.1 编写对应的配置类

2.1.1 [`TestProperties.java`](./src/main/java/com/demo/properties2/config/properties/TestProperties.java) 配置类  
2.1.2 [`TestProperties2.java`](./src/main/java/com/demo/properties2/config/properties/TestProperties2.java) 配置类2

### 2.2 编写配置文件

2.2.1 [`config.properties`](./src/main/resources/config.properties) 配置类  
2.2.2 [`config2.properties`](./src/main/resources/file/config2.properties) 配置文件2

### 2.3 测试src/test/java/com/demo/properties2/PropertiesTests.java

编写测试方法读取配置文件常量值 [`PropertiesTests.java`](./src/test/java/com/demo/properties2/PropertiesTests.java)

## 3 读取`XML`文件

### 3.1 编写对应的配置类

3.1.1 [`TestXml.java`](./src/main/java/com/demo/properties2/config/xml/TestXml.java) 配置类  
3.1.2 [`TestXml2.java`](./src/main/java/com/demo/properties2/config/xml/TestXml2.java) 配置类2

### 3.2 编写配置文件

3.2.1 [`config.xml`](./src/main/resources/config.xml) 配置类  
3.2.2 [`config.xml2`](./src/main/resources/file/config2.xml) 配置文件2

### 3.3 测试

编写测试方法读取配置文件常量值 [`XmlTests.java`](./src/test/java/com/demo/properties2/XmlTests.java)

## 4 读取`YML`文件

### 4.1 编写对应的配置类

4.1.1 [`TestYml.java`](./src/main/java/com/demo/properties2/config/yml/TestYml.java) 配置类  
4.1.2 [`TestYml2.java`](./src/main/java/com/demo/properties2/config/yml/TestYml2.java) 配置类2

### 4.2 编写对应的解析工厂类

4.2.1 [`YmlPropertySourceFactory.java`](./src/main/java/com/demo/properties2/factory/YmlPropertySourceFactory.java)
解析工厂类

### 4.3 编写配置文件

4.3.1 [`config.yml`](./src/main/resources/config.yml) 配置类   
4.3.2 [`config.yml2`](./src/main/resources/file/config2.yml) 配置文件2

### 4.4 测试

编写测试方法读取配置文件常量值 [`YmlTests.java`](./src/test/java/com/demo/properties2/YmlTests.java)

## 5 读取`XML`文件或`YML`文件

测试 [`PropertiesAndXmlTests.java`](./src/test/java/com/demo/properties2/PropertiesAndXmlTests.java)