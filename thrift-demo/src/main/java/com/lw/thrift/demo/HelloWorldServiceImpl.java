package com.lw.thrift.demo;

import org.apache.thrift.TException;

public class HelloWorldServiceImpl implements HelloWorldService.Iface{

	public String sayHello(User user) throws TException {
		return user.getName() + "," + user.getEmial();
	}
}
