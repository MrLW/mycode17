package com.lw.demo.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * submit一个Runnable
 * @author liwen
 *
 */
public class ExecutorServiceDemo2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" async task exec");
			}
		}) ;
		System.out.println("get之前");
		// 该方法会阻塞
		Object obj = future.get();
		System.out.println("obj:" + obj);
		executor.shutdown();
	}
}
