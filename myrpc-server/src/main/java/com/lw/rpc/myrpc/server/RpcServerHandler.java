package com.lw.rpc.myrpc.server;

import java.util.Map;

import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.lw.rpc.myrpc.common.bean.RpcRequest;
import com.lw.rpc.myrpc.common.bean.RpcResponse;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

	private final Map<String, Object> handlerMap;

	public RpcServerHandler(Map<String, Object> handlerMap) {
		this.handlerMap = handlerMap;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
		RpcResponse response = new RpcResponse();
		response.setRequestId(request.getRequestId());
		Object result = handle(request);
		response.setResult(result);
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
	
	private Object handle(RpcRequest request) throws Exception {
		String className = request.getClassName();
		Object serviceBean = handlerMap.get(className);
		Class<? extends Object> serviceClazz = serviceBean.getClass();
		String methodName = request.getMethodName();
		Class<?>[] parameterTypes = request.getParameterTypes();
		Object[] params = request.getParameters();

		FastClass serviceFastClass = FastClass.create(serviceClazz);
		FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
		return serviceFastMethod.invoke(serviceBean, params);
	}

}
