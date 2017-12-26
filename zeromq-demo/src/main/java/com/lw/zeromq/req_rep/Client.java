package com.lw.zeromq.req_rep;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Client {

	public static void main(String[] args) {
		Context context = ZMQ.context(1);
		// Socket to talk to server
		System.out.println("Connecting to hello world server…");
		// 设置zeromq的连接模式
		Socket client = context.socket(ZMQ.REQ);
		// 建立连接
		client.connect("tcp://localhost:5555");
		for (int requestNbr = 0; requestNbr != 100; requestNbr++) {
			String request = "Hello";
			System.out.println("Sending Hello " + requestNbr);
			// 发送数据,并且为这个客户端设置一个flag标志
			client.send(request.getBytes(), 0);
			// 接收对应标志的数据
			byte[] reply = client.recv(0);
			System.out.println("Received " + new String(reply) + " " + requestNbr);
		}
		client.close();
		context.term();
	}
}
