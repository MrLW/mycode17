package com.lw.demo.keywords;

/**
 * @author liwen
 * @date:2017��12��4�� ����1:24:54
 * @Function:volatile: 1��ǿ�н�volatile�ؼ������ε�ֵд������
 *                     2�����߳�2�޸�ʹ��volatile�ؼ������ε�ֵ,�ᵼ���߳�1�Ĺ����ڴ��л������������Ч
 *                     3�����߳�1�Ĺ����ڴ滺�������Ч,��˻�������ж�ȡ
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
