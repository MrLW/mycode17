package com.lw.rpc.myrpc.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lw.rpc.myrpc.api.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-client.xml")
public class TestClient {
	
	@Autowired
	private RpcProxy rpcProxy;
	
	@Test
	public void helloService() {
		System.out.println(rpcProxy);
		HelloService helloService = rpcProxy.create(HelloService.class);
		String result = helloService.hello("world");
		System.out.println(result);
	}
}
