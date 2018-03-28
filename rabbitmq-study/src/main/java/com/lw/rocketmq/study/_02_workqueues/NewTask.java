package com.lw.rocketmq.study._02_workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
	private final static String QUEUE_NAME = "durable-workqueue";
	private final static String HOST = "192.168.1.120";

	public static void main(String[] args) {

		ConnectionFactory factory = new ConnectionFactory();
		Channel channel = null;
		Connection connection = null;
		factory.setHost(HOST);
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			// ������:�־û�
			boolean durable = true ;
			channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
			String message = getMessage(args);
			// ������:�־û�����
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "Hello World!";
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}

}
