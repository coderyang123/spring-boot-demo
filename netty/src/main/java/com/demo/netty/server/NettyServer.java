package com.demo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端
 *
 * @author yueyang
 * @since 2022-09-14 10:11:00
 */
@Slf4j
public class NettyServer {
  private final int port;

  public NettyServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) {
    new NettyServer(18080).run();
  }

  /** 启动Netty服务 */
  private void run() {
    // Netty的Reactor线程池，初始化了一个NioEventLoop数组，用来处理I/O操作,如接受新的连接和读/写数据
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      start(group);
    } catch (InterruptedException e) {
      log.error("服务端未知错误", e);
    } finally {
      try {
        close(group);
      } catch (InterruptedException e) {
        log.error("关闭EventLoopGroup错误", e);
      }
    }
  }

  /**
   * @param group 事件循环组
   * @throws InterruptedException 终端异常
   */
  private void start(EventLoopGroup group) throws InterruptedException {
    // 用于启动NIO服务
    ServerBootstrap bootstrap = buildBootstrap(group);

    // 绑定服务器，该实例将提供有关IO操作的结果或状态的信息
    ChannelFuture channelFuture = bootstrap.bind().sync();
    log.info("在【{}】上开启监听", channelFuture.channel().localAddress());

    // 阻塞操作，closeFuture()开启了一个channel的监听器（这期间channel在进行各项工作），直到连接断开
    channelFuture.channel().closeFuture().sync();
  }

  /**
   * 构建一个服务端辅助启动类
   *
   * @param group 事件循环组
   * @return 客户端辅助启动类
   */
  private ServerBootstrap buildBootstrap(EventLoopGroup group) {
    ServerBootstrap bootstrap = new ServerBootstrap();

    // 通过工厂方法设计模式实例化一个channel
    bootstrap
        .group(group)
        .channel(NioServerSocketChannel.class)
        // 设置监听端口
        .localAddress(new InetSocketAddress(port))
        // 指定此套接口排队的最大连接个数
        .option(ChannelOption.SO_BACKLOG, 1024)
        // 两小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        // ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的Channel,用于把许多自定义的处理类增加到pipeline上来
        // 配置childHandler来通知一个关于消息处理的InfoServerHandler实例
        .childHandler(new ServerChannelInitializer());

    return bootstrap;
  }

  /**
   * 关闭EventLoopGroup并释放所有资源，包括所有创建的线程
   *
   * @param group 事件循环组
   */
  private void close(EventLoopGroup group) throws InterruptedException {
    group.shutdownGracefully().sync();
  }
}
