# Spring Boot集成WebSocket

> 本项目主要演示如何在项目里面通过`spring-boot-starter-websocket`来集成`WebSocket`实现服务端-客户端简单通信

## 1 [`pom.xml`](./pom.xml)

主要添加的项目依赖

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
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>
</dependencies>
```

## 2 [`WebSocketHandler.java`](./src/main/java/com/demo/websocket2/handler/WebSocketHandler.java)

`WebSocket`处理器相关配置，重写了父类`AbstractWebSocketHandler`的四个方法：

- `afterConnectionEstablished`，和客户端链接成功的时候触发该方法；
- `handleTransportError`，和客户端连接失败的时候触发该方法；
- `afterConnectionClosed`，和客户端断开连接的时候触发该方法；
- `handleTextMessage`，和客户端建立连接后，处理客户端发送的请求。

## 3 [`WebSocketConfigure.java`](./src/main/java/com/demo/websocket2/config/WebSocketConfigure.java)

`WebSocket`配置类

## 4 [`Websocket2Application.java`](./src/main/java/com/demo/websocket2/Websocket2Application.java)

启动类添加`@EnableWebSocket`注解，开启`Websocket`功能

## 5 [`client.html`](./src/main/resources/static/client.html)

页面主要是在`JS`里写与后台进行`Socket`通信的逻辑  
`SockJS`对象包含几个常用的实用方法：

- `onopen`，和服务端讲了连接后的回调方法；
- `onmessage`，服务端返回消息时的回调方法；
- `onclose`，和服务端断开连接的回调方法；
- `send`，发送消息给服务端；
- `close`，断开和服务端的连接；

## 6 访问`http://localhost:8080/client.html`

