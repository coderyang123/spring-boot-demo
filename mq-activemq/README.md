# ActiveMQ 使用案例

# 1.基础环境搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-activemq</artifactId>
    </dependency>
</dependencies>
```

## 1.2 消息服务

[MessageService.java](src/main/java/com/demo/activemq/service/api/MessageService.java)  
[MessageServiceImpl.java](src/main/java/com/demo/activemq/service/impl/MessageServiceImpl.java)

## 1.3 消费监听器

[MessageListener.java](src/main/java/com/demo/activemq/listener/MessageListener.java)

## 1.4 控制层

[MessageController.java](src/main/java/com/demo/activemq/controller/MessageController.java)

## 1.5 配置文件

[application.yml](src/main/resources/application.yml)

# 2.生产消费消息演示

## 2.1 手动消费

```
// 生产消息
@Override
public void sendMessageId(String id) {
    log.info("待发送消息，id:{}", id);
    jmsMessagingTemplate.convertAndSend("message.queue.id", id);
}

// 消费消息
@Override
public String consumeMessage() {
    String id = jmsMessagingTemplate.receiveAndConvert("message.queue.id", String.class);
    log.info("已完成消息发送，id:{}, ", id);
    return id;
}
```

## 2.2 自动消费

```
// 在监听器中配置
@JmsListener(destination = "message.queue.name")
    public void receiveName(String name) {
    log.info("已完成消息发送业务，name:{}", name);
}
```

## 2.3 自动消费且将消息流转到下一个监听器处理

```
// 在监听器中配置
@JmsListener(destination = "message.queue.data")
@SendTo("message.queue.data.next")
    public String receiveData(String data) {
    log.info("已完成消息发送业务，data:{}", data);
    return data;
}

@JmsListener(destination = "message.queue.data.next")
    public void receiveDataNext(String data) {
    log.info("已完成流转来的消息发送业务，data.next:{}", data);
}
```