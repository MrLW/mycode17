package com.lw.activemq.p2p;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.lw.activemq.pojo.Order;

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
			session = conn.createSession(true, Session.CLIENT_ACKNOWLEDGE);
			// 创建队列,要么是Queue,要么是Topic
			destination = session.createQueue("helloworld");
			consumer = session.createConsumer(destination);
			// 接收消息

			consumer.setMessageListener(new MessageListener() {

				public void onMessage(Message message) {
					ObjectMessage obj = (ObjectMessage) message;
					try {
						Order object = (Order) obj.getObject();
						System.out.println("consumer收到消息:" + object);
					} catch (JMSException e) {
						e.printStackTrace();
					}
					try {
						message.acknowledge();
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
