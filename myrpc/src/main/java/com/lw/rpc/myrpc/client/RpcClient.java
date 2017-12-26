package com.lw.rpc.myrpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lw.rpc.myrpc.bean.RpcRequest;
import com.lw.rpc.myrpc.bean.RpcResponse;
import com.lw.rpc.myrpc.net.RpcDecoder;
import com.lw.rpc.myrpc.net.RpcEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {
	private static final Logger LOGGER = LoggerFactory.getLogger(RpcClient.class);

	private String host;
	private int port;

	private RpcResponse response;

	private final Object obj = new Object();

	public RpcClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
		this.response = response;
		synchronized (obj) {
			obj.notifyAll(); // �յ���Ӧ�������߳�
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error("client caught exception", cause);
		ctx.close();
	}

	public RpcResponse send(RpcRequest request) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline().addLast(new RpcEncoder(RpcRequest.class)) // �� RPC ������б��루Ϊ�˷�������
							.addLast(new RpcDecoder(RpcResponse.class)) // �� RPC ��Ӧ���н��루Ϊ�˴�����Ӧ��
							.addLast(RpcClient.this); // ʹ�� RpcClient ���� RPC ����
				}
			}).option(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().writeAndFlush(request).sync();

			synchronized (obj) {
				obj.wait(); // δ�յ���Ӧ��ʹ�̵߳ȴ�
			}

			if (response != null) {
				future.channel().closeFuture().sync();
			}
			return response;
		} finally {
			group.shutdownGracefully();
		}
	}

}
