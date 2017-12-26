package com.lw.rpc.myrpc.server;

import com.lw.rpc.myrpc.api.HelloService;

@RPCService(HelloService.class)
public class HelloServiceImpl implements HelloService {

	public String hello(String name) {
		return "hello," + name ;
	}

}
