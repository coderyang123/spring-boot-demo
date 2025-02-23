package com.demo.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端
 *
 * @author yueyang
 * @since 2022-09-14 10:10:00
 */
@Slf4j
public class NettyClient {
  private final String host;
  private final int port;

  public NettyClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public static void main(String[] args) {
    new NettyClient("127.0.0.1", 18080).run();
  }

  /** 配置相应的参数，提供连接到远端的方法 */
  public void run() {
    // IO线程池
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      start(group);
    } catch (InterruptedException e) {
      log.error("客户端未知错误", e);
    } finally {
      try {
        close(group);
      } catch (InterruptedException e) {
        log.error("关闭EventLoopGroup错误", e);
      }
    }
  }

  private void start(EventLoopGroup group) throws InterruptedException {
    // 客户端辅助启动类
    Bootstrap bootstrap = buildBootstrap(group);

    // 连接到远程节点；等待连接完成
    ChannelFuture future = bootstrap.connect().sync();

    // 发送消息到服务器端，编码格式是utf-8
    future.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Server", CharsetUtil.UTF_8));

    // 阻塞操作，closeFuture()开启了一个channel的监听器（这期间channel在进行各项工作），直到连接断开
    future.channel().closeFuture().sync();
  }

  /**
   * 构建一个客户端辅助启动类
   *
   * @param group 事件循环组
   * @return 客户端辅助启动类
   */
  private Bootstrap buildBootstrap(EventLoopGroup group) {
    Bootstrap bootstrap = new Bootstrap();
    bootstrap
        .group(group)
        // 实例化一个Channel
        .channel(NioSocketChannel.class)
        .remoteAddress(new InetSocketAddress(host, port))
        // 进行通道初始化配置
        .handler(new ClientChannelInitializer());

    return bootstrap;
  }

  /**
   * 关闭EventLoopGroup并释放所有资源，包括所有创建的线程
   *
   * @param group 事件循环组
   * @throws InterruptedException 中断异常
   */
  private void close(EventLoopGroup group) throws InterruptedException {
    group.shutdownGracefully().sync();
  }
}
