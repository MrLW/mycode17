package com.lw.utils;

public class CommonUtil {
	/**
	 * ��ӡ
	 */
	public static void print(int[] a) {
		for (int i : a) {
			System.out.println("a=>" + i);
		}
	}

	/**
	 * ��������������Ԫ�ص�ֵ
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
