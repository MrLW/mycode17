package com.lw.zeromq.push_pull;

import java.util.concurrent.atomic.AtomicInteger;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Pull {

	public static void main(String[] args) {
		final AtomicInteger num = new AtomicInteger(0);
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					Context ctx = ZMQ.context(1);
					Socket pull = ctx.socket(ZMQ.PULL);
					pull.connect("tcp://localhost:8888");
					while (true) {
						String msg = new String(pull.recv());
						System.out.println(Thread.currentThread().getName() + "msg:" + msg);
					}
				};
			}.start();
		}

	}
}
