package com.lw.thrift.demo;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

	public static void main(String[] args) {
		try {
			String ip = "127.0.0.1";
			int port = 9000;
			int timeOut = 1000;
			// 创建Transport
			TTransport transport = new TSocket(ip, port, timeOut);
			// 创建TProtocol
			TProtocol protocol = new TBinaryProtocol(transport);
			// 基于TTransport和TProtocol创建Client
			HelloWorldService.Client client = new HelloWorldService.Client(protocol);
			transport.open();
			User user = new User();
			user.setEmial("lw@qq.com");
			user.setName("lw");
			String msg = client.sayHello(user);
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
