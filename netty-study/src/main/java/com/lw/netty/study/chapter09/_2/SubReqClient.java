package com.lw.netty.study.chapter09._2;

import com.lw.netty.study.chapter09.protobuf.SubscribeRespProto;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class SubReqClient {

	public void connect(int port, String host) throws Exception {
		try {
			EventLoopGroup group = new NioEventLoopGroup();
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							// ProtobufDecoder仅仅负责编码,不支持读半包,因此需要额外处理
							pipeline.addLast(new ProtobufVarint32FrameDecoder());
							pipeline.addLast(
									new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
							pipeline.addLast(new ProtobufEncoder());
							pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
							pipeline.addLast(new SubReqClientHandler());
						}
					});

			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} finally {

		}
		
	}
	
	class SubReqClientHandler extends ChannelHandlerAdapter{
	}
	
	
	public static void main(String[] args) throws Exception {
		new SubReqClient().connect(8080, "localhost");
	}
}
