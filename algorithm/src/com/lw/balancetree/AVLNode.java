package com.lw.balancetree;

/**
 * @author liwen
 * @date:2018年2月6日 上午11:09:58
 * @Function: 平衡二叉树节点
 * @param <T>
 * @version 1.0
 */
public class AVLNode<T extends Comparable> {

	public AVLNode<T> left;// 左结点

	public AVLNode<T> right;// 右结点

	public T data;
	// 当前结点的高度,对比于二叉搜索树多了一个height字段
	public int height;

	public AVLNode(T data) {
		this(null, null, data);
	}

	public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
		this(left, right, data, 0);
	}

	public AVLNode(AVLNode<T> left, AVLNode<T> right, T data, int height) {
		this.left = left;
		this.right = right;
		this.data = data;
		this.height = height;
	}
}
