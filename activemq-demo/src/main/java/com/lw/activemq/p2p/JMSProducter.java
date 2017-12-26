package com.lw.activemq.p2p;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.management.j2ee.statistics.JMSProducerStats;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * @author liwen
 * @date:2017年12月13日 下午2:27:15
 * @Function: 生产者
 * @version 1.0
 */
public class JMSProducter {

	private static ActiveMQConnectionFactory connectionFactory;
	private static Connection conn;
	private static Queue destination;
	private static Session session;
	private static MessageProducer producer;

	public static void main(String[] args) {

		try {
			// 创建连接工厂
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
			// 获取连接
			conn = connectionFactory.createConnection();
			
			conn.start();
			// 创建session
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 创建消息队列
			destination = session.createQueue("helloworld");
			// 创建生产者
			producer = session.createProducer(destination);
			
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 发送消息
			sendMessage(session, producer);

			// 提交
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void sendMessage(Session session, MessageProducer producer2) throws Exception {
		for (int i = 0; i < 10; i++) {
			// 创建文本消息
			TextMessage msg = session.createTextMessage("activemq 发送消息:" + i);
			System.out.println("Producter 发送消息:" + i );
			producer.send(msg);
		}
	}
}
