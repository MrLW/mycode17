package com.lw.chapter06;

/**
 * @author liwen
 * @date:2018年2月9日 上午11:23:45
 * @Function:在堆排序中一般用最大堆,最小堆一般应用构造优先队列
 * @version 1.0
 */
public class HeapSort {

	public static void main(String[] args) {
		HeapSort hs = new HeapSort();
		// 此处的-1不作为堆中的元素,只是让数组从1开始计数
		int a[] = { -1, 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		int heapSize = a.length - 1;
		hs.build_max_heap(a);
		hs.heapSort(a);
		hs.heapExtractMax(a, heapSize);
		hs.print(a);
	}

	/**
	 * 取出最大值
	 */
	public int heapMaximum(int[] a) {
		return a[1];
	}

	/**
	 * 取出最大元素的值,并从数组中取出
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
	 * 增加某个结点的值
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
	 * 插入元素
	 */
	public void maxHeapInsert(int[] a, int key, int heapSize) {
		heapSize = heapSize + 1;
		a[heapSize] = 0;
		heapIncreaseKey(a, heapSize, key);
	}

	/**
	 * 堆排序
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
	 * 建立最大堆
	 */
	public void build_max_heap(int[] a) {
		int i = a.length / 2;
		int heap_size = a.length - 1;
		for (; i >= 1; i--) {
			max_heapify(a, i, heap_size);
		}
	}

	/**
	 * 对于树高度为h来说,时间复杂度为O(h)
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
	 * 父结点
	 */
	public int parent(int i) {
		return i / 2;
	}

	/**
	 * 左孩子
	 */
	public int left(int i) {
		return 2 * i;
	}

	/**
	 * 右孩子
	 */
	public int right(int i) {
		return 2 * i + 1;
	}

	/**
	 * 交换数组中两个元素的值
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
	 * 打印
	 */
	public void print(int[] a) {
		for (int i : a) {
			System.out.println("a=>" + i);
		}
	}

}
