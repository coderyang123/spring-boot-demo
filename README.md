# Spring Boot场景整合

> 本项目主要演示如何在项目将`Spring Boot`和各种技术进行整合并实战

## ✴️ 开发环境

- **JDK 11**
- **Maven 3.8**
- **IntelliJ IDEA ULTIMATE 2020.2**
- **MySQL 5.7.36**

## ⚙️ IDE配置

查看 [🔗SETTING](settings/README.md) 文件

## 🗓️ 开发计划

查看 [🔗TODO](TODO.md) 文件

## 🎉 各 Module 介绍

| Module 名称                                                                    | Module 介绍                                                    |
|------------------------------------------------------------------------------|--------------------------------------------------------------|
| [websocket](websocket/README.md)                                             | Spring Boot 集成 websocket，后端主动推送前端当前时间戳                       |
| [cache-redis](cache-redis/README.md)                                         | Spring Boot 集成 redis，缓存系统参数                                  |
| [async](async/README.md)                                                     | Spring Boot 使用 Spring 原生提供的异步任务支持，实现异步执行任务                   |                           |
| [file-upload-download](file-upload-download/README.md)                       | Spring Boot 文件上传下载案例                                         |  
| [openfeign](openfeign/README.md)                                             | openfeign 远程调用案例                                             |
| [guava](guava/README.md)                                                     | guava 工具集使用案例                                                |
| [all-learning](all-learning/README.md)                                       | Spring Boot 集成 Mybatis-Plus、参数校验、本地缓存、统一异常处理案例               |
| [fastjson](fastjson/README.md)                                               | Fastjson 使用案例演示                                              |
| [easyexcel](easyexcel/README.md)                                             | Excel 文件读取和写入案例演示                                            |
| [properties](properties/README.md)                                           | 配置文件常量读取案例演示                                                 |
| [settings](settings/README.md)                                               | Intellij IDEA 配置                                             |
| [customize-thread-pool](customize-thread-pool/README.md)                     | Spring Boot 自定义线程示例                                          |
| [package-war](package-war/README.md)                                         | 项目打包成 War 的案例                                                |
| [package-jar](package-jar/README.md)                                         | 项目打包成 Jar 的案例                                                |
| [websocket2](websocket2/README.md)                                           | Spring Boot 集成 websocket，简单客户端-服务端通信案例                       |
| [spring-security](spring-security/README.md)                                 | Spring Boot 集成 SpringSecurity，简单实现系统认值和授权的案例                 |
| [elasticsearch-high-level-client](elasticsearch-high-level-client/README.md) | Spring Boot 集成 elasticsearch 高级客户端完成基本 CURD 操作               |
| [druid](druid/README.md)                                                     | Spring Boot 集成 Druid 数据源演示案例                                 |
| [websocket3](websocket3/README.md)                                           | Spring Boot 集成 websocket，演示客户端-服务端、客户端-客户端一对一、客户端-客户端一对多通信案例 |
| [jwt](jwt/README.md)                                                         | Spring Boot 集成 JWT，演示项目鉴权案例                                  |
| [interceptor](interceptor/README.md)                                         | Spring Boot 开发拦截器案例                                          |
| [compress-uncompress](compress-uncompress/README.md)                         | 图片压缩和文件压缩解压缩案例                                               |
| [properties2](properties2/README.md)                                         | properties、xml、yml配置文件内容读取案例演示                               |
| [spring-event](spring-event/README.md)                                       | Spring 事件流案例                                                 |
| [orm-mybatis](orm-mybatis/README.md)                                         | Spring Boot 集成 Mybatis ORM 框架案例                              |
| [orm-mybatis-helper](orm-mybatis-helper/README.md)                           | Mybatis ORM 框架集成通用Mapper和分页助手案例                              |
| [orm-jpa](orm-jpa/README.md)                                                 | Spring Boot 集成 JPA ORM 框架案例                                  |
| [aop](aop/README.md)                                                         | Spring AOP 应用于日志的案例                                          |
| [collection-jetty](collection-jetty/README.md)                               | Spring Boot 集成 Jetty 容器案例                                    |
| [collection-undertow](collection-undertow/README.md)                         | Spring Boot 集成 Undertow 容器案例                                 |
| [orm-mybatis-plus](orm-mybatis-plus/README.md)                               | Spring Boot 集成 Mybatis-Plus ORM 框架案例                         |
| [task](task/README.md)                                                       | Spring Boot 集成定时任务案例                                         |
| [mq-kafka](mq-kafka/README.md)                                               | Spring Boot 集成 Kafka 示例                                      |
| [multi-datasource-mybatis](multi-datasource-mybatis/README.md)               | Spring Boot 集成 Mybatis-Plus ORM 框架使用多数据源案例                   |
| [web-service](web-service/README.md)                                         | Spring Boot 集成 Web Service 案例                                |
| [httpclient](httpclient/README.md)                                           | HTTP连接池案例                                                    |
| [xstream](xstream/README.md)                                                 | XStream 使用案例                                                 |
| [local-cache](local-cache/README.md)                                         | 使用 ConcurrentHashMap 实现本地缓存案例                                |
| [email](email/README.md)                                                     | 发送邮件案例                                                       |
| [regex](regex/README.md)                                                     | 正则表达式案例                                                      |
| [log-log4j2](log-log4j2/README.md)                                           | Spring Boot 集成 Log4j2 日志案例                                   |
| [log-logback](log-logback/README.md)                                         | Spring Boot 集成 Logback 日志案例                                  |
| [docker-log](docker-log/README.md)                                           | 应用打包成 Docker 镜像，然后运行容器输出日志至宿主机指定目录案例                         |
| [cache-spring](cache-spring/README.md)                                       | Spring Boot 内置缓存使用案例                                         |
| [cache-ehcache](cache-ehcache/README.md)                                     | Spring Boot 集成 Ehcache 缓存案例                                  |
| [cache-redis2](cache-redis2/README.md)                                       | Spring Boot 集成 Redis 缓存案例                                    |
| [quartz](quartz/README.md)                                                   | Spring Boot 集成 Quartz 定时任务案例                                 |
| [mq-activemq](mq-activemq/README.md)                                         | Spring Boot 集成 ActiveMQ 案例                                   |
| [mq-rabbitmq-direct](mq-rabbitmq-direct/README.md)                           | Spring Boot 集成 RabbitMQ 直连模式案例                               |
| [mq-rabbitmq-topic](mq-rabbitmq-topic/README.md)                             | Spring Boot 集成 RabbitMQ 主题模式案例                               |
| [mq-rocketmq](mq-rocketmq/README.md)                                         | Spring Boot 集成 RocketMQ 主题模式案例                               |
| [mq-redis](mq-redis/README.md)                                               | Spring Boot 集成 Redis 发布订阅模式案例                                |
| [nosql-mongo](nosql-mongo/README.md)                                         | Spring Boot 集成 Mongo 数据库案例                                   |
| [nosql-redis](nosql-redis/README.md)                                         | Spring Boot 集成 Redis 数据库案例                                   |
| [netty](netty/README.md)                                                     | Spring Boot 集成 Netty 实现服务端-客户端通信案例                           |
| [aop2](aop2/README.md)                                                       | Spring AOP 应用于日志的案例2                                         |
| [valid](valid/README.md)                                                     | Spring Boot 集成参数校验案例                                         |

Copyleft (c) 2020-2023 yueyang