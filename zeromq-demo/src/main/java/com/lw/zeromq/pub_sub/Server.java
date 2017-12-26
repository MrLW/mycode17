package com.lw.zeromq.pub_sub;

import java.util.Random;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		// Prepare our context and publisher
		Context context = ZMQ.context(1);
		Socket publisher = context.socket(ZMQ.PUB);// pub
		publisher.bind("tcp://*:5556");

		// Initialize random number generator
		Random srandom = new Random(System.currentTimeMillis());
		while (!Thread.currentThread().isInterrupted()) {
			// Get values that will fool the boss
			int zipcode, temperature, relhumidity;
			zipcode = 10000 + srandom.nextInt(10000);
			temperature = srandom.nextInt(215) - 80 + 1;
			relhumidity = srandom.nextInt(50) + 10 + 1;

			// Send message to all subscribers
			String update = String.format("%05d %d %d", zipcode, temperature, relhumidity);
			publisher.send(update, ZMQ.PAIR);
		}
		publisher.close();
		context.term();
	}

}
