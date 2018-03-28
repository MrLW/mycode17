package com.lw.chapter06;

/**
 * @author liwen
 * @date:2018��2��9�� ����11:23:45
 * @Function:�ڶ�������һ��������,��С��һ��Ӧ�ù������ȶ���
 * @version 1.0
 */
public class HeapSort {

	public static void main(String[] args) {
		HeapSort hs = new HeapSort();
		// �˴���-1����Ϊ���е�Ԫ��,ֻ���������1��ʼ����
		int a[] = { -1, 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		int heapSize = a.length - 1;
		hs.build_max_heap(a);
		hs.heapSort(a);
		hs.heapExtractMax(a, heapSize);
		hs.print(a);
	}

	/**
	 * ȡ�����ֵ
	 */
	public int heapMaximum(int[] a) {
		return a[1];
	}

	/**
	 * ȡ�����Ԫ�ص�ֵ,����������ȡ��
	 */
	public int heapExtractMax(int a[], int heapSize) {
		if (heapSize < 1) {
			throw new RuntimeException("heap underflow");
		}
		int max = a[1];
		a[1] = a[heapSize];
		heapSize = heapSize - 1;
		max_heapify(a, 1, heapSize);
		return max;
	}

	/**
	 * ����ĳ������ֵ
	 */
	public void heapIncreaseKey(int[] a, int i, int key) {
		if (key < a[i]) {
			throw new RuntimeException("new key is smaller than current key");
		}
		a[i] = key;
		while (i > 1 && a[parent(i)] < a[i]) {
			swap(a, i, parent(i));
			i = parent(i);
		}

	}

	/**
	 * ����Ԫ��
	 */
	public void maxHeapInsert(int[] a, int key, int heapSize) {
		heapSize = heapSize + 1;
		a[heapSize] = 0;
		heapIncreaseKey(a, heapSize, key);
	}

	/**
	 * ������
	 */
	public void heapSort(int a[]) {
		build_max_heap(a);
		int len = a.length - 1;
		for (int i = len; i >= 2; i--) {
			swap(a, 1, i);
			len = len - 1;
			max_heapify(a, 1, len);
		}

		print(a);
	}

	/**
	 * ��������
	 */
	public void build_max_heap(int[] a) {
		int i = a.length / 2;
		int heap_size = a.length - 1;
		for (; i >= 1; i--) {
			max_heapify(a, i, heap_size);
		}
	}

	/**
	 * �������߶�Ϊh��˵,ʱ�临�Ӷ�ΪO(h)
	 */
	public void max_heapify(int[] a, int i, int heap_size) {
		int l = left(i);
		int r = right(i);
		int largest;
		if (l <= heap_size && a[l] > a[i]) {
			largest = l;
		} else {
			largest = i;
		}

		if (r <= heap_size && a[r] > a[largest]) {
			largest = r;
		}
		if (largest != i) {
			swap(a, largest, i);
			max_heapify(a, largest, heap_size);
		}
	}

	/**
	 * �����
	 */
	public int parent(int i) {
		return i / 2;
	}

	/**
	 * ����
	 */
	public int left(int i) {
		return 2 * i;
	}

	/**
	 * �Һ���
	 */
	public int right(int i) {
		return 2 * i + 1;
	}

	/**
	 * ��������������Ԫ�ص�ֵ
	 */
	public void swap(int a[], int x, int y) {
		try {
			int t = a[x];
			a[x] = a[y];
			a[y] = t;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("x:" + x + ";y:" + y);
		}
	}

	/**
	 * ��ӡ
	 */
	public void print(int[] a) {
		for (int i : a) {
			System.out.println("a=>" + i);
		}
	}

}
