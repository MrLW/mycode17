package com.lw.zeromq.pub_sub;

import java.io.IOException;
import java.util.StringTokenizer;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		ZMQ.Context context = ZMQ.context(1);
		
		System.out.println("Collecting updates from weather server");
		// 创建订阅模式的socket
		Socket subscriber = context.socket(ZMQ.SUB);
		// 连接
		subscriber.connect("tcp://localhost:5556");

		// Subscribe to zipcode, default is NYC, 10001
		String filter = (args.length > 0) ? args[0] : "";
		subscriber.subscribe(filter.getBytes());

		// Process 100 updates
		int update_nbr;
		long total_temp = 0;
		for (update_nbr = 0; update_nbr < 100; update_nbr++) {
			// Use trim to remove the tailing '0' character
			String string = subscriber.recvStr(0).trim();
			StringTokenizer sscanf = new StringTokenizer(string, " ");
			int zipcode = Integer.valueOf(sscanf.nextToken());
			int temperature = Integer.valueOf(sscanf.nextToken());
			int relhumidity = Integer.valueOf(sscanf.nextToken());

			total_temp += temperature;
		}
		System.out.println("Average temperature for zipcode '" + filter + "' was " + (int) (total_temp / update_nbr));
		System.out.println("...");
		System.in.read();
		subscriber.close();
		context.term();
	}
}
