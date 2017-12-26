package com.lw.demo.collection;

/**
 * @author liwen
 * @date:2017��12��18�� ����11:48:35
 * @Function: ��Happen��Before���������İ�ȫ��DCL����
 * @version 1.0
 */
public class HappenBeforeSingleton {
	// ˼��:Ϊʲôʹ��volatile�ؼ���
	private static volatile HappenBeforeSingleton singleton;

	private HappenBeforeSingleton() {
	}

	public HappenBeforeSingleton getInstance() {
		if (singleton == null) {
			synchronized (HappenBeforeSingleton.class) {
				if (singleton == null) {
					singleton = new HappenBeforeSingleton();
				}
			}
		}
		return singleton;
	}
}
