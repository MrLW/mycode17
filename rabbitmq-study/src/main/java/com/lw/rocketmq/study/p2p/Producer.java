package com.lw.rocketmq.study.p2p;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * @author liwen
 * @date:2018年2月2日 上午11:00:53
 * @Function:生产者
 * @version 1.0
 */
public class Producer {

	public static void main(String[] args) throws Exception, TimeoutException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		// 设置RabbitMQ地址
		factory.setHost("192.168.1.120");
		// 获取连接
		Connection conn = factory.newConnection();
		// 获取通道
		Channel channel = conn.createChannel();
		String exchangeName = "hello-exchange"; 
		// 申明交换器和绑定规则(4种)
		channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true);
		// 发布消息
		String routingKey = "routingKey";
		byte[] bytes = "quit".getBytes();
		channel.basicPublish(exchangeName, routingKey, null, bytes);
		// 关闭连接
		channel.close();
		conn.close();
	}
}
