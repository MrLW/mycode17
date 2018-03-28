package com.lw.netty.study.source.server;

import java.net.InetAddress;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 *  其实和ChannelInitializer一样,都是ChannelInboundHandlerAdapter的子类
 * @author liwen
 *
 */
public class HelloServerHandler extends SimpleChannelInboundHandler<String>{
	 @Override
	 protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
	     // 收到消息直接打印输出
	     System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
	     // 返回客户端消息 - 我已经接收到了你的消息
	     // ctx.writeAndFlush("Received your message !\n");
	     Channel channel = ctx.channel();
	     channel.writeAndFlush("Received your message !\n");
	 }
	 
	 /*
	  * 
	  * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
	  * 
	  * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
	  */
	 @Override
	 public void channelActive(ChannelHandlerContext ctx) throws Exception {
	     
	     System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !\n");
	     
	     ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
	     
	     super.channelActive(ctx);
	 }
}
