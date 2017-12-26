package com.lw.activemq.pub_sub;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

	private static ActiveMQConnectionFactory connectionFactory;
	private static Connection conn;
	private static Session session;
	private static MessageProducer producer;
	private static Topic topic;

	public static void main(String[] args) throws Exception {
		init();
		sendMessage(producer, session);
		destory();
	}

	private static void destory() throws Exception {
		conn.close();
	}

	private static void sendMessage(MessageProducer producer, Session session) throws Exception {
		for (int i = 0; i < 10; i++) {
			TextMessage message = session.createTextMessage("ActiveMQ 发送的消息" + i);
			System.out.println("发送消息：" + "ActiveMQ 发布的消息" + i);
			producer.send(message);
		}
	}

	private static void init() throws Exception {
		connectionFactory = new ActiveMQConnectionFactory();
		conn = connectionFactory.createConnection();
		conn.start();
		session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		topic = session.createTopic("firstTopic");
		producer = session.createProducer(topic);
	}
}
