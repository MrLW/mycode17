package com.lw.zeromq.pub_sub;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Publisher {

	public static void main(String[] args) {
		System.out.println("===========publisher start=============");
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.PUB);
		socket.setLinger(5000);
		socket.setSndHWM(0);
		socket.bind("tcp://localhost:6666");

		// �����sleep,����ֶ�ʧ��Ϣ�����ss
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		for (int i = 0; i < 10; i++) {
			String pubstr = "task message-" + i;
			socket.send(pubstr, 0);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		 // ������־,���Ķ��ж�
		socket.send("END", 0);
		System.out.println("===========publisher end=============");
		socket.close();
		context.term();
	}
}
