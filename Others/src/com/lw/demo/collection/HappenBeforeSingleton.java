package com.lw.demo.collection;

/**
 * @author liwen
 * @date:2017年12月18日 上午11:48:35
 * @Function: 由Happen—Before衍生出来的安全的DCL单例
 * @version 1.0
 */
public class HappenBeforeSingleton {
	// 思考:为什么使用volatile关键字
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
