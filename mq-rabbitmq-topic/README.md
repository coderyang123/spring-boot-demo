# RabbitMQ操作示例-主题模式

# 1.基本框架搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
</dependencies>
```

## 1.2 配置类

[RabbitDirectConfig.java](src/main/java/com/demo/rabbitmq/config/RabbitTopicConfig.java)

> 注意此处和直连模式的不同点，这里在绑定队列和交换机的时候，可以模糊指定路由键  
> 规则：  
> `*` 表示一个单词，且该单词是必须出现的  
> `#` 表示任意数量单词

## 1.3 消费监听类

[MessageListener.java](src/main/java/com/demo/rabbitmq/listener/MessageListener.java)

## 1.4 消息服务类

[MessageService.java](src/main/java/com/demo/rabbitmq/service/api/MessageService.java)  
[MessageServiceImpl.java](src/main/java/com/demo/rabbitmq/service/impl/MessageServiceImpl.java)

## 1.5 控制类

[MessageController.java](src/main/java/com/demo/rabbitmq/controller/MessageController.java)

## 1.6 配置文件

[application.yml](src/main/resources/application.yml)

# 2.生产消费消息测试

## 2.1 请求接口

`POST http://localhost:8080/message/id/1`

## 2.2 管理界面

`http://IP:PORT/`