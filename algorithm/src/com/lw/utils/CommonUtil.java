package com.lw.utils;

public class CommonUtil {
	/**
	 * 打印
	 */
	public static void print(int[] a) {
		for (int i : a) {
			System.out.println("a=>" + i);
		}
	}

	/**
	 * 交换数组中两个元素的值
	 */
	public static void swap(int a[], int x, int y) {
		try {
			int t = a[x];
			a[x] = a[y];
			a[y] = t;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("x:" + x + ";y:" + y);
		}
	}
}
