package com.lw.rpc.myrpc.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RPCApp {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("spring-server.xml");
	}
}
