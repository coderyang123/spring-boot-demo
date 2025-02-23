# Netty服务端-客户端通信案例

# 1.主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>io.netty</groupId>
        <artifactId>netty-all</artifactId>
        <version>4.1.81.Final</version>
    </dependency>
</dependencies>
```

# 2.服务端

- [`NettyServer.java`](src/main/java/com/demo/netty/server/NettyServer.java)
- [`ServerChannelInitializer.java`](src/main/java/com/demo/netty/server/ServerChannelInitializer.java)
- [`ServerHandler.java`](src/main/java/com/demo/netty/server/ServerHandler.java)

# 3.客户端

- [`NettyClient.java`](src/main/java/com/demo/netty/client/NettyClient.java)
- [`ClientChannelInitializer.java`](src/main/java/com/demo/netty/client/ClientChannelInitializer.java)
- [`ClientHandler.java`](src/main/java/com/demo/netty/client/ClientHandler.java)
