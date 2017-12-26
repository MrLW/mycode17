package com.lw.rpc.myrpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RPCApp {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("spring-server.xml");
	}
}
