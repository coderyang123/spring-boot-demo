# Spring Boot 集成 Kafka 示例

# 1.主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

# 2.添加配置文件 [`application.yml`](src/main/resources/application.yml)

# 3.编写生产者 [`KafkaProducer.java`](src/main/java/com/demo/kafka/controller/KafkaProducer.java)

# 4.编写消费者 [`KafkaConsumer.java`](src/main/java/com/demo/kafka/config/KafkaConsumer.java)

# 5.Kafka docker-compose 脚本 [`KafkaConsumer.java`](src/main/resources/script/docker-compose.yml)