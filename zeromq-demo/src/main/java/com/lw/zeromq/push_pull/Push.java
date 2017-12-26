package com.lw.zeromq.push_pull;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Push {

	public static void main(String[] args) {
		Context ctx = ZMQ.context(1);
		// 1、 获取对应类型的socket
		Socket push = ctx.socket(ZMQ.PUSH);
		// 2、绑定
		push.bind("tcp://localhost:8888");
		for (int i = 0; i < 1000000; i++) {
			// 3、发送消息
			push.send(("hello-" + i + "").getBytes());
		}
		// 4、关闭资源
		push.close();
		ctx.term();
	}
}
