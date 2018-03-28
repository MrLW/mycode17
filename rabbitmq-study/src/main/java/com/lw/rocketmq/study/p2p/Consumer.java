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
		// ��������
		Connection conn = factory.newConnection();
		final Channel channel = conn.createChannel();
		// ����������
		String exchangeName = "hello-exchange";
		channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true);
		// ��������
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
					System.out.println("·��:" + routingKey + ";��Ϣ����:" + contentType);
					long deliveryTag = envelope.getDeliveryTag();
					// ȷ����Ϣ
					channel.basicAck(deliveryTag, false);
					System.out.println("��Ϣ������:" + new String(body, "utf-8"));
				}
			});
		}

	}
}
