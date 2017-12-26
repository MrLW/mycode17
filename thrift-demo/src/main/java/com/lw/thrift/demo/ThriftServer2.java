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
 * @date:2017��11��17�� ����11:00:03
 * @Function: ������
 * @version 1.0
 */
public class ThriftServer2 {

	public static void main(String[] args) {
		try {
			int port = 8091;
			// ����processor
			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
			// ����transport ������ nonblocking
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
			// ����protocol ���ݴ���Э��
			TCompactProtocol.Factory protocol = new TCompactProtocol.Factory();
			// ����transport ���ݴ��䷽ʽ ��������Ҫ�����ַ�ʽ����
			TFramedTransport.Factory transport = new TFramedTransport.Factory();
			TNonblockingServer.Args serverArgs = new TNonblockingServer.Args(serverTransport);
			serverArgs.processor(tprocessor);
			serverArgs.transportFactory(transport);
			serverArgs.protocolFactory(protocol);
			// ���������� �����Ƿ�����
			TServer server = new TNonblockingServer(serverArgs);
			// ��������
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
