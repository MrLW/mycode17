package com.lw.rpc.myrpc.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lw.rpc.myrpc.common.bean.RpcRequest;
import com.lw.rpc.myrpc.common.bean.RpcResponse;
import com.lw.rpc.myrpc.common.coder.RpcDecoder;
import com.lw.rpc.myrpc.common.coder.RpcEncoder;
import com.lw.rpc.myrpc.registry.ServiceRegistry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liwen
 * @date:2017��11��27�� ����11:01:11
 * @Function: RPC������
 * @version 1.0
 */
public class RPCServer implements ApplicationContextAware, InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(RPCServer.class);

	private String serverAddress;

	private ServiceRegistry serviceRegistry;

	private Map<String, Object> handlerMap = new HashMap<String, Object>(); // ��Žӿ�����������֮���ӳ���ϵ

	public RPCServer(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public RPCServer(String serverAddress, ServiceRegistry serviceRegistry) {
		this.serverAddress = serverAddress;
		this.serviceRegistry = serviceRegistry;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("RPCServer.afterPropertiesSet()");
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel channel) throws Exception {
							channel.pipeline().addLast(new RpcDecoder(RpcRequest.class)) // �� RPC ������н��루Ϊ�˴�������
									.addLast(new RpcEncoder(RpcResponse.class)) // �� RPC ��Ӧ���б��루Ϊ�˷�����Ӧ��
									.addLast(new RpcServerHandler(handlerMap)); // ���� RPC ����
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

			String[] array = serverAddress.split(":");
			String host = array[0];
			int port = Integer.parseInt(array[1]);

			ChannelFuture future = bootstrap.bind(host, port).sync();
			LOGGER.debug("server started on port {}", port);

			if (serviceRegistry != null) {
				serviceRegistry.register(serverAddress); // ע������ַ
			}

			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		System.out.println("RPCServer.setApplicationContext()");
		// ��ȡ���д��� RpcService ע���
		// Spring Bean
		Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RPCService.class);

		if (MapUtils.isNotEmpty(serviceBeanMap)) {
			for (Object serviceBean : serviceBeanMap.values()) {
				String interfaceName = serviceBean.getClass().getAnnotation(RPCService.class).value().getName();
				handlerMap.put(interfaceName, serviceBean);
			}
		}
	}

}
