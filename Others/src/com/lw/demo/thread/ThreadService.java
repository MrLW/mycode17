package com.lw.demo.thread;
/**
 * @author liwen
 * @date:2018年3月20日 下午4:00:32
 * @Function: 测试线程标识
 * @version 1.0
 */
public class ThreadService {

	static class ThreadA extends Thread {
		private Object lock;

		public ThreadA(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("当前线程状态:"+ isInterrupted());
					while (!isInterrupted()) { 
						System.out.println("wait start");
						Thread.sleep(1000);
						System.out.println("wait end");
					}
				}
				System.out.println("while code");
			} catch (InterruptedException e) {
				System.out.println("Exception:InterruptedException1;当前标志位:" + isInterrupted());
			}

		}
	}

	public static void main(String[] args) throws Exception {
		Object lock = new Object();
		ThreadA threadA = new ThreadA(lock);
		threadA.start();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:InterruptedException2");
		}
		threadA.interrupt();
		System.out.println("调用了中断方法之后,此时中断标志位:"+ threadA.isInterrupted());
	}
}
