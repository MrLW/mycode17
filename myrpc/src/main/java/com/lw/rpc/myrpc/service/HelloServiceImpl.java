package com.lw.rpc.myrpc.service;

@RPCService(HelloService.class)
public class HelloServiceImpl implements HelloService {

	public String hello(String name) {
		return "hello," + name ;
	}

}
