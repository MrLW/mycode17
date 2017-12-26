package com.lw.thrift.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

public class ThriftClient2 {

	public static void main(String[] args) {
		try {
			String ip = "127.0.0.1";
			int port = 8091;
			int timeOut = 1000;
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket(ip, port, timeOut);
			TProtocolFactory tprotocol = new TCompactProtocol.Factory();
			HelloWorldService.AsyncClient asyncClient = new HelloWorldService.AsyncClient(tprotocol, clientManager,
					transport);
			// 客户端异步调用
			User user = new User();
			user.setName("lw");
			user.setEmial("lw@qq.com");
			CountDownLatch latch = new CountDownLatch(1);
			AsynInvokerCallback callBack = new AsynInvokerCallback(latch);
//			asyncClient.sayHello(user, callBack);
			latch.await(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
