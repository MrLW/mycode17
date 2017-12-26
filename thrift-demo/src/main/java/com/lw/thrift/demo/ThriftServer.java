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
			// ����˿�
			int port = 9000;
			// ����transport����
			TServerSocket tServerSocket = new TServerSocket(port) ;
			// ����Protocol
			Factory factory = new TBinaryProtocol.Factory();
			// ��processor transport protocol���뵽������server��
			TServer.Args serverArgs = new TServer.Args(tServerSocket);
			serverArgs.processor(processor);
			serverArgs.protocolFactory(factory);
			// ������������� �趨����
			TServer server = new TSimpleServer(serverArgs);
			// ��������
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
