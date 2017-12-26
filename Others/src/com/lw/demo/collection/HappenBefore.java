package com.lw.demo.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liwen
 * @date:2017年12月11日 上午11:54:25
 * @Function: 测试重排序
 * @version 1.0
 */
public class HappenBefore {

	private int a;

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public static void main(String[] args) {
		HappenBefore before = new HappenBefore();
		new Thread() {
			public void run() {
				before.setA(1);
			};
		}.start();

		new Thread() {
			public void run() {
			};
		}.start();
	}

}
