package com.lw.zeromq.pub_sub;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Subscriber {

	public static void main(String[] args) {
		System.out.println("===========subscriber start=============");
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.SUB);
		socket.connect("tcp://localhost:6666");
		socket.subscribe("task".getBytes());
		while (true) {
			byte[] res = socket.recv(0);
			String resStr = new String(res);
			System.out.println("substring is =" + resStr);
			if ("END".equalsIgnoreCase(resStr)) {
				break;
			}
		}
		System.out.println("===========subscriber end=============");
		socket.close();
		context.term();
	}
}
