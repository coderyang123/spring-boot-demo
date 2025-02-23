package com.demo.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端消息处理器
 *
 * @author yueyang
 * @since 2022-09-14 10:09:00
 */
@Slf4j
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

  /** 处理接收到的消息 */
  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
    log.info("接收到来自服务端的消息：{}", byteBuf.toString(CharsetUtil.UTF_8));
  }

  /** 处理IO事件的异常 */
  @Override
  public void exceptionCaught(ChannelHandlerContext handlerContext, Throwable cause) {
    log.error("执行出现错误", cause);
    handlerContext.close();
  }
}
