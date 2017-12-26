package com.lw.demo.keywords;

/**
 * @author liwen
 * @date:2017年12月4日 下午1:32:48
 * @Function:volatile 只保证可见性,不保证原子性
 * @version 1.0
 */
public class VolatileDemo02 {

	public volatile int inc = 0;

	public void increase() {
		inc++; // 不是原子操作
	}

	public static void main(String[] args) {
		final VolatileDemo02 test = new VolatileDemo02();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}

		while (Thread.activeCount() > 1) // 保证前面的线程都执行完
			Thread.yield(); // 让掉主线程,让其他线程去竞争执行
		System.out.println(test.inc);
	}
}
