package com.lw.zeromq.req_rep;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Client {

	public static void main(String[] args) {
		Context context = ZMQ.context(1);
		// Socket to talk to server
		System.out.println("Connecting to hello world server��");
		// ����zeromq������ģʽ
		Socket client = context.socket(ZMQ.REQ);
		// ��������
		client.connect("tcp://localhost:5555");
		for (int requestNbr = 0; requestNbr != 100; requestNbr++) {
			String request = "Hello";
			System.out.println("Sending Hello " + requestNbr);
			// ��������,����Ϊ����ͻ�������һ��flag��־
			client.send(request.getBytes(), 0);
			// ���ն�Ӧ��־������
			byte[] reply = client.recv(0);
			System.out.println("Received " + new String(reply) + " " + requestNbr);
		}
		client.close();
		context.term();
	}
}
