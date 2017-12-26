package com.lw.demo.keywords;

/**
 * @author liwen
 * @date:2017��12��4�� ����1:32:48
 * @Function:volatile ֻ��֤�ɼ���,����֤ԭ����
 * @version 1.0
 */
public class VolatileDemo02 {

	public volatile int inc = 0;

	public void increase() {
		inc++; // ����ԭ�Ӳ���
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

		while (Thread.activeCount() > 1) // ��֤ǰ����̶߳�ִ����
			Thread.yield(); // �õ����߳�,�������߳�ȥ����ִ��
		System.out.println(test.inc);
	}
}
