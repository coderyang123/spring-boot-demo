# Spring Boot 集成邮件功能

# 1.主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
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

    <!--jasypt配置文件加解密-->
    <dependency>
        <groupId>com.github.ulisesbocchio</groupId>
        <artifactId>jasypt-spring-boot-starter</artifactId>
        <version>3.0.4</version>
    </dependency>

    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.7.22</version>
    </dependency>
</dependencies>
```

# 2.项目结构

## 2.1 加密工具类

[JasyptUtils](src/main/java/com/demo/email/util/JasyptUtils.java)

## 2.2 邮件服务类

[MailServiceImpl](src/main/java/com/demo/email/service/impl/MailServiceImpl.java)

## 2.3 加密工具类

[JasyptUtils](src/main/java/com/demo/email/util/JasyptUtils.java)

## 2.4 加密工具测试类

[JasyptUtilsTest](src/test/java/com/demo/email/util/JasyptUtilsTest.java)

## 2.5 邮件服务测试类

[MailServiceImplTest](src/test/java/com/demo/email/service/impl/MailServiceImplTest.java)

## 2.6 配置文件

[application](src/main/resources/application.yml)

# 3.测试

## 3.1 开启QQ邮箱SMTP服务

**设置** -> **账户** -> **POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务** -> **开启POP3/SMTP服务** -> **拿到授权码**

## 3.2 填写授权码

- 在[JasyptUtils](src/main/java/com/demo/email/util/JasyptUtils.java)和[application](src/main/resources/application.yml)
  的password处填写获取的授权码
- 在[JasyptUtilsTest](src/test/java/com/demo/email/util/JasyptUtilsTest.java)生成加密密码
- 在[application](src/main/resources/application.yml)重新填写加密后的密码
- 在[MailServiceImplTest](src/test/java/com/demo/email/service/impl/MailServiceImplTest.java)发送短信测试吧