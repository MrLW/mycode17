package com.lw.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerMain {

	public static void main(String[] args) throws Exception {
		// 创建服务
		HelloService helloService = new HelloServiceImpl() ;
		// 注册断开
		//LocateRegistry.createRegistry(8080);
		// 这里会构造一个Stub对象,
		Naming.bind("rmi://localhost:8080/helloService", helloService); 
		
		System.out.println("serverMain provide rpc service now");
	}
}
