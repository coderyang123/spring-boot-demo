# Spring Boot集成WebSocket

> 本项目主要演示如何在项目里面通过`spring-boot-starter-websocket`来集成`WebSocket`实现主动向前端推送消息

# 1.主要添加的项目依赖

[`pom.xml`](pom.xml)

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

# 2.WebSocket相关配置

[`WebSocketConfig.java`](src/main/java/com/demo/websocket/config/WebSocketConfig.java) [`WebSocketServer.java`](src/main/java/com/demo/websocket/config/WebSocketServer.java)

# 3.模拟请求

[`WebSocketController.java`](src/main/java/com/demo/websocket/controller/WebSocketController.java)

# 4.页面

[`index.html`](src/main/resources/static/index.html)

页面主要是在`JS`里写订阅后台配置的`WebSocket`地址的逻辑

# 5.验证

## 5.1 访问页面查看设备在线情况

`http://localhost:18801/index.html`

## 5.2 发送请求模拟设备故障，观察页面变化

`POST http://localhost:18801/open/socket/onReceive?id=1&pwd=123456`