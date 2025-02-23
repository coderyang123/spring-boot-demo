# Spring 事件流示例

## 1.定义

- 定义一个事件[DemoEvent](./src/main/java/com/demo/springevent/config/DemoEvent.java): 实现一个继承自
  ApplicationEvent，并且写相应的构造函数；
- 定义一个事件监听者[DemoListener](./src/main/java/com/demo/springevent/config/DemoListener.java)：实现
  ApplicationListener 接口，重写
  onApplicationEvent() 方法；
- 使用事件发布者[DemoPublisher](./src/main/java/com/demo/springevent/config/DemoPublisher.java)发布消息: 可以通过
  ApplicationEventPublisher 的 publishEvent() 方法发布消息。

## 2.测试 [SpringEventApplicationTests](./src/test/java/com/demo/springevent/SpringEventApplicationTests.java)