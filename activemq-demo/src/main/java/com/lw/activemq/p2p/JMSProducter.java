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
 * @date:2017��12��13�� ����2:27:15
 * @Function: ������
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
			// �������ӹ���
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
			// ��ȡ����
			conn = connectionFactory.createConnection();
			
			conn.start();
			// ����session
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// ������Ϣ����
			destination = session.createQueue("helloworld");
			// ����������
			producer = session.createProducer(destination);
			
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// ������Ϣ
			sendMessage(session, producer);

			// �ύ
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
			// �����ı���Ϣ
			TextMessage msg = session.createTextMessage("activemq ������Ϣ:" + i);
			System.out.println("Producter ������Ϣ:" + i );
			producer.send(msg);
		}
	}
}