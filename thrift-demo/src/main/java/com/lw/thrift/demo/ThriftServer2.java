package com.lw.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

/**
 * @author liwen
 * @date:2017年11月17日 上午11:00:03
 * @Function: 非阻塞
 * @version 1.0
 */
public class ThriftServer2 {

	public static void main(String[] args) {
		try {
			int port = 8091;
			// 创建processor
			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
			// 创建transport 非阻塞 nonblocking
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
			// 创建protocol 数据传输协议
			TCompactProtocol.Factory protocol = new TCompactProtocol.Factory();
			// 创建transport 数据传输方式 非阻塞需要用这种方式传输
			TFramedTransport.Factory transport = new TFramedTransport.Factory();
			TNonblockingServer.Args serverArgs = new TNonblockingServer.Args(serverTransport);
			serverArgs.processor(tprocessor);
			serverArgs.transportFactory(transport);
			serverArgs.protocolFactory(protocol);
			// 创建服务器 类型是非阻塞
			TServer server = new TNonblockingServer(serverArgs);
			// 开启服务
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
