package com.lw.netty.study.protocolstack.http;

import java.net.URI;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;

public class HttpClient {

	public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient() ;
		client.connect("localhost",8000);
	}

	private void connect(String host, int port) throws Exception {
		EventLoopGroup loopGroup = new NioEventLoopGroup() ;
		Bootstrap b = new Bootstrap() ;
		b.group(loopGroup);
		b.channel(NioSocketChannel.class);
		//SO_BACKLOG:标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
		// 是否启用心跳保活机制
		b.option(ChannelOption.SO_KEEPALIVE, true);
		b.handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new HttpResponseDecoder());
				pipeline.addLast(new HttpRequestEncoder());
				pipeline.addLast(new HttpClientHandler());
			}
		});
		ChannelFuture cf = b.connect(host, port).sync();
		URI uri = new URI("http://127.0.0.1:8000");
		String msg = "Are you ok ?" ;
		FullHttpRequest request = new DefaultFullHttpRequest(
				HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toASCIIString(),Unpooled.wrappedBuffer(msg.getBytes()));
		// 构建http请求
		request.headers().set(HttpHeaderNames.HOST, host);
		request.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderNames.CONNECTION);
		request.headers().set(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
		request.headers().set("messageType","normal");
		request.headers().set("businessType", "testServerState");
		// 发送http请求
		cf.channel().writeAndFlush(request);
		cf.channel().closeFuture().sync();
	}
}
