package com.lw.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerMain {

	public static void main(String[] args) throws Exception {
		// ��������
		HelloService helloService = new HelloServiceImpl() ;
		// ע��Ͽ�
		//LocateRegistry.createRegistry(8080);
		// ����ṹ��һ��Stub����,
		Naming.bind("rmi://localhost:8080/helloService", helloService); 
		
		System.out.println("serverMain provide rpc service now");
	}
}
