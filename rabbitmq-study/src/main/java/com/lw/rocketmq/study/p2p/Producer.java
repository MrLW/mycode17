package com.lw.rocketmq.study.p2p;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * @author liwen
 * @date:2018��2��2�� ����11:00:53
 * @Function:������
 * @version 1.0
 */
public class Producer {

	public static void main(String[] args) throws Exception, TimeoutException {
		// �������ӹ���
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		// ����RabbitMQ��ַ
		factory.setHost("192.168.1.120");
		// ��ȡ����
		Connection conn = factory.newConnection();
		// ��ȡͨ��
		Channel channel = conn.createChannel();
		String exchangeName = "hello-exchange"; 
		// �����������Ͱ󶨹���(4��)
		channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true);
		// ������Ϣ
		String routingKey = "routingKey";
		byte[] bytes = "quit".getBytes();
		channel.basicPublish(exchangeName, routingKey, null, bytes);
		// �ر�����
		channel.close();
		conn.close();
	}
}
