# Spring Boot集成WebSocket

> 本项目主要演示如何在项目里面通过`spring-boot-starter-websocket`来集成`WebSocket`实现服务端-客户端简单通信、客户端一对一、客户端一对多通信

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

  <dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.76</version>
  </dependency>
</dependencies>
```

## 2 [`WebSocketConfiguration.java`](./src/main/java/com/demo/websocket3/config/WebSocketConfiguration.java)

`WebSocket`配置类

## 3 [`OneToService.java`](./src/main/java/com/demo/websocket3/handler/OneToService.java)

客户端-服务端通信案例

## 4 [`OneToOne.java`](./src/main/java/com/demo/websocket3/handler/OneToOne.java)

客户端-客户端一对一通信案例

## 5 [`OneToService.java`](./src/main/java/com/demo/websocket3/handler/OneToService.java)

客户端-客户端一对多通信案例

## 6 [`index.html`](./src/main/resources/static/index.html)

页面主要是在`JS`里写与后台进行`Socket`通信的逻辑，在第20行根据不同的通信模式选用不同的通信点

### 6.1 客户端-服务端通信模式

在客户端-服务端通信模式下，在输入框填写消息，点击发送，页面会接受到服务端回复的消息

### 6.2 客户端-客户端一对一通信模式

在客户端-客户端一对一通信模式下，开A、B两个页面，模拟两个用户在线，用户ID分别为0、1，在页面A输入框填写“消息
1”，表示给1客户端发送消息，点击发送，页面B会接受到服务端转发来的消息

### 6.3 客户端-客户端一对多通信模式

在客户端-客户端一对多通信模式下，开A、B、C三个页面，模拟三个用户在线，用户ID分别为0、1、2，在页面A输入框填写，点击发送，页面B和C会接受到服务端转发来的消息

## 7 访问`http://localhost:8080/index.html`

