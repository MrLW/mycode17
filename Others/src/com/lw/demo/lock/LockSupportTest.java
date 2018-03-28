package com.lw.demo.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

	public static void main(String[] args) throws Exception {
		String blocker = new String("游戏");
		Thread t = new Thread(new Runnable(	) {
			
			@Override
			public void run() {
				System.out.println("周末打游戏");
				LockSupport.park(blocker);
				System.out.println("出去吃饭");
			}
		});
		t.start();
		Thread.sleep(3000);
		Object obj = LockSupport.getBlocker(t);
		System.out.println("老妈叫吃饭;brocker:" + obj);
		LockSupport.unpark(t);
	}
}
