package com.lw.thrift.anno;

import java.net.InetSocketAddress;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;

public class ServerClient {

	public static void main(String[] args) throws Exception {
		ThriftClientManager clientManager = new ThriftClientManager();
		FramedClientConnector connector = new FramedClientConnector(new InetSocketAddress("localhost", 8899));

		User user = new User();
		user.setName("test");
		user.setEmail("test@qq.com");

		HelloService helloService = clientManager.createClient(connector, HelloService.class).get();
		String hi = helloService.sayHello(user);
		System.out.println(hi);
	}
}
