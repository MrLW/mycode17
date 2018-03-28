package com.lw.rocketmq.study._05_rpc;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RPCClient {

	private static final String EXCHANGE_NAME = "logs";
	private final static String HOST = "192.168.1.120";
	private static String callbackQueueName;
	public static void main(String[] args) throws  Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		callbackQueueName = channel.queueDeclare().getQueue();
		BasicProperties props = new BasicProperties.Builder().replyTo(callbackQueueName).build();
		channel.basicPublish("", "rpc_queue", props, "message".getBytes());
		channel.close();
		connection.close();
	}
	
}
