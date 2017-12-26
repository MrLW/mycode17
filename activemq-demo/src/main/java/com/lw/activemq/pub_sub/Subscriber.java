package com.lw.activemq.pub_sub;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {
	private static ActiveMQConnectionFactory connectionFactory;
	private static Connection conn;
	private static Session session;
	private static Topic topic;
	private static MessageConsumer consumer;

	public static void main(String[] args) throws Exception {
		
		init() ;
		
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage msg = (TextMessage) message ;
				try {
					System.out.println("ฯ๛ทั:" + msg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void init() throws Exception {
		connectionFactory = new ActiveMQConnectionFactory();
		conn = connectionFactory.createConnection();
		conn.start();
		session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		topic = session.createTopic("firstTopic");
		consumer = session.createConsumer(topic);
	}
	
	

}
