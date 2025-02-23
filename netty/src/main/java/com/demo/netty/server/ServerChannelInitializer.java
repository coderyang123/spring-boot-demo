package com.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.concurrent.TimeUnit;

/**
 * 服务端通道初始化器
 *
 * @author yueyang
 * @since 2022-09-29 15:06:00
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel channel) {
    // 添加编解码
    //    channel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
    //    channel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

    // 读取超时 在设置时间内没有读取操作
    channel.pipeline().addLast(new ReadTimeoutHandler(60L, TimeUnit.SECONDS));

    // 写入超时 在设置时间内没有写入操作
    channel.pipeline().addLast(new WriteTimeoutHandler(60L, TimeUnit.SECONDS));

    // 添加处理器
    channel.pipeline().addLast(new ServerHandler());
  }
}
