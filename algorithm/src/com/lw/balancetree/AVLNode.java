package com.lw.balancetree;

/**
 * @author liwen
 * @date:2018��2��6�� ����11:09:58
 * @Function: ƽ��������ڵ�
 * @param <T>
 * @version 1.0
 */
public class AVLNode<T extends Comparable> {

	public AVLNode<T> left;// ����

	public AVLNode<T> right;// �ҽ��

	public T data;
	// ��ǰ���ĸ߶�,�Ա��ڶ�������������һ��height�ֶ�
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
