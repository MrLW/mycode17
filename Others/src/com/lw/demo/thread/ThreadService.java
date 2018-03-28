package com.lw.demo.thread;
/**
 * @author liwen
 * @date:2018��3��20�� ����4:00:32
 * @Function: �����̱߳�ʶ
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
					System.out.println("��ǰ�߳�״̬:"+ isInterrupted());
					while (!isInterrupted()) { 
						System.out.println("wait start");
						Thread.sleep(1000);
						System.out.println("wait end");
					}
				}
				System.out.println("while code");
			} catch (InterruptedException e) {
				System.out.println("Exception:InterruptedException1;��ǰ��־λ:" + isInterrupted());
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
		System.out.println("�������жϷ���֮��,��ʱ�жϱ�־λ:"+ threadA.isInterrupted());
	}
}
