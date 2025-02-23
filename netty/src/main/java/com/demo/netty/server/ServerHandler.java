package com.demo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端消息处理器
 *
 * @author yueyang
 * @since 2022-09-14 10:10:00
 */
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

  /** 客户端连接会触发 */
  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    log.info("客户端连接了");
  }

  /** 客户端发消息会触发 */
  @Override
  public void channelRead(ChannelHandlerContext handlerContext, Object message) {
    // 处理收到的数据，并反馈消息到到客户端
    ByteBuf info = (ByteBuf) message;
    log.info("接收到来自客户端的消息:{}", info.toString(CharsetUtil.UTF_8));

    // 写入并发送信息到远端（客户端）
    handlerContext.writeAndFlush(Unpooled.copiedBuffer("你好，我是服务端，我已经收到你发送的消息", CharsetUtil.UTF_8));
  }

  /** 发生异常触发 */
  @Override
  public void exceptionCaught(ChannelHandlerContext handlerContext, Throwable cause) {
    // 出现异常的时候执行的动作（打印并关闭通道）
    log.error("执行出现错误", cause);
    handlerContext.close();
  }
}
