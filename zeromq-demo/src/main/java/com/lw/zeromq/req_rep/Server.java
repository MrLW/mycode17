package com.lw.zeromq.req_rep;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ZMQ.Context context = ZMQ.context(1);
			Socket responder = context.socket(ZMQ.REP);
			responder.bind("tcp://*:5555");
			while (!Thread.currentThread().isInterrupted()) {
				byte[] bs = responder.recv(0);
				System.out.println("receive " + new String(bs));
				Thread.sleep(1000);
				String reply = "World !";
				responder.send(reply.getBytes(), 0);
			}
			responder.close();
			context.term();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
