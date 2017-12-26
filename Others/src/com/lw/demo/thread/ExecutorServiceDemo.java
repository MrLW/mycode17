package com.lw.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ��ִ��һ������
 * 
 * @author liwen
 */
public class ExecutorServiceDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("asyn task execute ");
			}
		});

	}
}
