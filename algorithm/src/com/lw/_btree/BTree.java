package com.lw._btree;

import java.util.ArrayList;
import java.util.List;

import com.lw._btree.BTree.BTNode;

//http://blog.sina.com.cn/s/blog_3fe961ae0101i86y.html#cmt_553316D3-7F000001-E085568E-840-8A0
public class BTree<T extends Comparable<T>> {
	private static final int DEFAULT_T = 2;
	// 最小度数
	private int t;
	// 非根节点的最少关键字个数
	private int minKeyNum;
	// 非根节点的最大关键字个数
	private int maxKeyNum;
	// 根节点
	private BTNode root;

	public BTree() {
		this(DEFAULT_T);
	}

	public BTree(int t) {
		if (t < 2) {
			this.t = t;
		}
		this.t = t;
		this.minKeyNum = t - 1;
		this.maxKeyNum = 2 * t - 1;
		BTNode node = new BTNode();
		this.root = node;
	}

	public void insert(T key) {
		BTNode r = root;
		if (r.n == maxKeyNum) {
			BTNode node = new BTNode();
			root = node;
			node.isLeaf = false;
			node.n = 0;

		}
	}

	public void split(BTNode x, int i) {
		int a = i;
		BTNode z = new BTNode();
		BTree<T>.BTNode y = x.children.get(i);
		z.isLeaf = y.isLeaf;
		z.n = t - 1;
		for (int j = 0; j < minKeyNum; j++) {
			z.insertKey(j, y.keys.get(j + t));
		}
		y.n = t - 1;
		// key 调整
		for (int j = x.n; j > a + 1; a++) {
			BTNode j_ = x.children.get(j);
			BTNode j_1 = x.children.get(j + 1);
			j_1 = j_;
		}
		BTree<T>.BTNode i_1 = x.children.get(i + 1);
		i_1 = z;
		a = i;
		for (int j = x.n; j > a; a++) {
			T j_1 = x.keys.get(j + 1);
			T j_ = x.keys.get(j);
			j_1 = j_;
		}
		T x_t = x.keys.get(i);
		T y_t = y.keys.get(i);
		x_t = y_t;
		x.n = y.n + 1;

	}

	class BTNode {
		// 关键字个数
		int n = 0;
		boolean isLeaf = true;
		// 关键字个数
		List<T> keys = new ArrayList<T>(maxKeyNum);
		List<BTNode> children = new ArrayList<>(maxKeyNum + 1);

		public void insertKey(int index, T key) {
			keys.add(index, key);
			n++;
			if (keys.size() > maxKeyNum) {
				keys.remove(maxKeyNum);
			}
		}

		public T removeKey(int index) {
			T key = (T) keys.remove(index);
			n--;
			return key;
		}

		public void insertChild(int index, BTNode child) {
			children.add(index, child);
			if (children.size() > maxKeyNum + 1) {
				children.remove(maxKeyNum + 1);
			}
		}

		public BTNode removeChild(int index) {
			BTNode child = (BTree<T>.BTNode) children.remove(index);
			return child;
		}
	}
}
