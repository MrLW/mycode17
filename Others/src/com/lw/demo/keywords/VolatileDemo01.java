package com.lw.demo.keywords;

/**
 * @author liwen
 * @date:2017年12月4日 下午1:24:54
 * @Function:volatile: 1、强行将volatile关键字修饰的值写入主存
 *                     2、当线程2修改使用volatile关键字修饰的值,会导致线程1的工作内存中缓存变量缓存无效
 *                     3、当线程1的工作内存缓存变量无效,因此会从主存中读取
 * @version 1.0
 */
public class VolatileDemo01 {

	private static volatile boolean stop = false;

	public static void main(String[] args) throws InterruptedException {
		new Thread() {
			public void run() {
				while (!stop) {
					System.out.println("thread-1");
				}
			};
		}.start();
		Thread.sleep(10);

		stop = true;

	}
}
