package com.lw.rmi;

import java.rmi.Naming;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		// �������
		HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:8080/helloService");
		
		System.out.println("rmi���񷵻صĽ��:" + helloService.sayHello("����"));
	}
}
