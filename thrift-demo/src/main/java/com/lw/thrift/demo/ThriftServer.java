package com.lw.thrift.demo;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import com.lw.thrift.demo.HelloWorldService.Iface;
import com.lw.thrift.demo.HelloWorldService.Processor;

public class ThriftServer {

	public static void main(String[] args) {
		try {
			System.out.println("start server ");
			Processor<Iface> processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
			// 服务端口
			int port = 9000;
			// 创建transport阻塞
			TServerSocket tServerSocket = new TServerSocket(port) ;
			// 创建Protocol
			Factory factory = new TBinaryProtocol.Factory();
			// 将processor transport protocol设入到服务器server中
			TServer.Args serverArgs = new TServer.Args(tServerSocket);
			serverArgs.processor(processor);
			serverArgs.protocolFactory(factory);
			// 定义服务器类型 设定参数
			TServer server = new TSimpleServer(serverArgs);
			// 开启服务
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
