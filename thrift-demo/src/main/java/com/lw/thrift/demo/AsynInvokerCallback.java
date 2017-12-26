package com.lw.thrift.demo;

import java.util.concurrent.CountDownLatch;

import org.apache.thrift.async.AsyncMethodCallback;

public class AsynInvokerCallback implements AsyncMethodCallback<HelloWorldService.AsyncClient.sayHello_call>{

	private CountDownLatch latch;

	public AsynInvokerCallback(CountDownLatch latch) {
		this.latch = latch;
	}

	/**
	 * �첽�������,�ص��÷���
	 *
	 * @param response
	 */
	public void onComplete(HelloWorldService.AsyncClient.sayHello_call response) {
		try {
			System.out.println("AsynInvokerCallback response: " + response.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}

	/**
	 * �첽���ó���ص�����
	 *
	 * @param exception
	 */
	public void onError(Exception exception) {
		latch.countDown();
	}
}
