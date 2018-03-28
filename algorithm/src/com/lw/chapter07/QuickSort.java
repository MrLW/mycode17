package com.lw.chapter07;

import com.lw.utils.CommonUtil;

/**
 * @author liwen
 * @date:2018��2��10�� ����10:23:00
 * @Function: ��������
 * @version 1.0
 */
public class QuickSort {

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		int[] a = { 2, 8, 7, 1, 3, 5, 6, 4 };
		int p = 0, r = a.length - 1;
		qs.quickSort(a, p, r);
		CommonUtil.print(a);
	}

	public void quickSort(int[] a, int p, int r) {
		if (p < r) {
			int q = partition(a, p, r);
			quickSort(a, p, q - 1);
			quickSort(a, q + 1, r);
		}
	}

	/**
	 * ���������ԭַ���� 2, 8, 7, 1, 3, 5, 6, 4
	 */
	public int partition(int[] a, int p, int r) {
		int x = a[r];
		int i = p - 1;
		for (int j = p; j <= r - 1; j++) {
			if (a[j] <= x) {
				i = i + 1;
				CommonUtil.swap(a, i, j);
			}
		}
		CommonUtil.swap(a, i + 1, r);
		return i + 1;
	}

}
