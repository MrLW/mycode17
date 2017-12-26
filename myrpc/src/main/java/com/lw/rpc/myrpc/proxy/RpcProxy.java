package com.lw.rpc.myrpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import com.lw.rpc.myrpc.bean.RpcRequest;
import com.lw.rpc.myrpc.bean.RpcResponse;
import com.lw.rpc.myrpc.client.RpcClient;
import com.lw.rpc.myrpc.registry.ServiceDiscovery;

public class RpcProxy {
	private String serverAddress;
	private ServiceDiscovery serviceDiscovery;

	public RpcProxy(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public RpcProxy(ServiceDiscovery serviceDiscovery) {
		this.serviceDiscovery = serviceDiscovery;
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<?> interfaceClass) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						RpcRequest request = new RpcRequest(); // ��������ʼ�� RPC ����
						request.setRequestId(UUID.randomUUID().toString());
						request.setClassName(method.getDeclaringClass().getName());
						request.setMethodName(method.getName());
						request.setParameterTypes(method.getParameterTypes());
						request.setParameters(args);

						if (serviceDiscovery != null) {
							serverAddress = serviceDiscovery.discover(); // ���ַ���
						}

						String[] array = serverAddress.split(":");

						String host = array[0];
						int port = Integer.parseInt(array[1]);

						RpcClient client = new RpcClient(host, port); // ��ʼ�� RPC �ͻ���
						RpcResponse response = client.send(request); // ͨ�� RPC �ͻ��˷��� RPC ���󲢻�ȡ RPC ��Ӧ

						if (response.getError() != null) {
							throw response.getError();
						} else {
							return response.getResult();
						}
					}
				});
	}
}
