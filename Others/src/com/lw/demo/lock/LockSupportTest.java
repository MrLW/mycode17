package com.lw.demo.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

	public static void main(String[] args) throws Exception {
		String blocker = new String("��Ϸ");
		Thread t = new Thread(new Runnable(	) {
			
			@Override
			public void run() {
				System.out.println("��ĩ����Ϸ");
				LockSupport.park(blocker);
				System.out.println("��ȥ�Է�");
			}
		});
		t.start();
		Thread.sleep(3000);
		Object obj = LockSupport.getBlocker(t);
		System.out.println("����гԷ�;brocker:" + obj);
		LockSupport.unpark(t);
	}
}
