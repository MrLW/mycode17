package com.lw.rocketmq.study.p2p;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer {

	public static void main(String[] args) throws Exception, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setHost("192.168.1.120");
		// 建立连接
		Connection conn = factory.newConnection();
		final Channel channel = conn.createChannel();
		// 申明交换器
		String exchangeName = "hello-exchange";
		channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true);
		// 申明队列
		String queueName = channel.queueDeclare().getQueue();
		String routingKey = "routingKey";
		channel.queueBind(queueName, exchangeName, routingKey);

		while (true) {
			boolean autoAck = false;
			String consumerTag = "";
			channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					super.handleDelivery(consumerTag, envelope, properties, body);
					String routingKey = envelope.getRoutingKey();
					String contentType = properties.getContentType();
					System.out.println("路由:" + routingKey + ";消息类型:" + contentType);
					long deliveryTag = envelope.getDeliveryTag();
					// 确认消息
					channel.basicAck(deliveryTag, false);
					System.out.println("消息的内容:" + new String(body, "utf-8"));
				}
			});
		}

	}
}
