package com.lw.rmi;

import java.rmi.Remote;

public interface HelloService extends Remote{

	String sayHello(String someOne) ;
}
