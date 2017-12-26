package com.lw.rmi;

import java.rmi.Naming;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		// 引入服务
		HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:8080/helloService");
		
		System.out.println("rmi服务返回的结果:" + helloService.sayHello("李文"));
	}
}
