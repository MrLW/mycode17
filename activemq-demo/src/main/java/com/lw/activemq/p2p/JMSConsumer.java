package com.lw.activemq.p2p;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author liwen
 * @date:2017年12月13日 下午2:27:24
 * @Function: 消费者
 * @version 1.0
 */
public class JMSConsumer {

	private static org.apache.activemq.ActiveMQConnectionFactory connectionFactory;
	private static Connection conn;
	private static Session session;
	private static Queue destination;
	private static MessageConsumer consumer;

	public static void main(String[] args) {
		try {
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
			conn = connectionFactory.createConnection();
			conn.start();
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("helloworld");
			consumer = session.createConsumer(destination);
			// 接收消息
			/*
			 * while (true) { TextMessage text = (TextMessage) consumer.receive(); if (text
			 * != null) { System.out.println("consumer收到的消息:" + text.getText()); } else {
			 * break; } }
			 */
			consumer.setMessageListener(new MessageListener() {

				public void onMessage(Message message) {
					TextMessage text = (TextMessage) message;
					try {
						String msg = text.getText();
						System.out.println("consumer:" + msg);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
