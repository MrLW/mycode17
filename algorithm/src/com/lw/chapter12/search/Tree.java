package com.lw.chapter12.search;

public class Tree {

	// 树根
	Node root;

	// 查找
	public Node find(int key) {
		Node curNode = root;
		while (curNode != null && curNode.key != key) {
			if (key < curNode.key) {
				curNode = curNode.leftChild;
			} else {
				curNode = curNode.rightChild;
			}
		}
		return curNode;
	}

	// 最大
	public Node minNode() {
		Node minNode = root;
		while (minNode.leftChild != null) {
			minNode = minNode.leftChild;
		}
		return minNode;
	}

	// 最大
	public Node maxNode() {
		Node maxNode = root;
		while (maxNode.rightChild != null) {
			maxNode = maxNode.rightChild;
		}
		return maxNode;
	}

	// 插入
	public Node insert(int key, int value) {
		// root 为null的情况下,key+value组成root
		if (root == null) {
			root = new Node(key, value);
			return root;
		}

		Node curNode = root;
		Node parNode = root;
		boolean isLeftChild = true;

		while (curNode != null) {
			parNode = curNode;
			if (key < curNode.key) {
				curNode = curNode.leftChild;
				isLeftChild = true;
			} else {
				curNode = curNode.rightChild;
				isLeftChild = false;
			}
		}
		// 构建Node
		Node newNode = new Node(key, value);
		if (isLeftChild)
			parNode.leftChild = newNode;
		else
			parNode.rightChild = newNode;
		return parNode;
	}

	// 删除节点(比较复杂)
	public boolean delete(int key) {
		Node currentNode = root;
		Node parentNode = root;
		boolean isLeftChild = true;
		while (currentNode != null && currentNode.key != key) {
			parentNode = currentNode;
			if (key < currentNode.key) {
				currentNode = currentNode.leftChild;
				isLeftChild = true;
			} else {
				currentNode = currentNode.rightChild;
				isLeftChild = false;
			}
		}
		if (currentNode == null) {
			return false;
		}
		if (currentNode.leftChild == null && currentNode.rightChild == null) {
			// 要删除的节点为叶子节点
			if (currentNode == root)
				root = null;
			else if (isLeftChild)
				parentNode.leftChild = null;
			else
				parentNode.rightChild = null;
		} else if (currentNode.rightChild == null) {// 要删除的节点只有左孩子
			if (currentNode == root)
				root = currentNode.leftChild;
			else if (isLeftChild)
				parentNode.leftChild = currentNode.leftChild;
			else
				parentNode.rightChild = currentNode.leftChild;
		} else if (currentNode.leftChild == null) {// 要删除的节点只有右孩子
			if (currentNode == root)
				root = currentNode.rightChild;
			else if (isLeftChild)
				parentNode.leftChild = currentNode.rightChild;
			else
				parentNode.rightChild = currentNode.rightChild;
		} else { 
			// 要删除的节点既有左孩子又有右孩子
			// 思路：用待删除节点右子树中的key值最小节点的值来替代要删除的节点的值,然后删除右子树中key值最小的节点
			// 右子树key最小的节点一定不含左子树,所以删除这个key最小的节点一定是属于叶子节点或者只有右子树的节点
			Node directPostNode = getDirectPostNode(currentNode);
			currentNode.key = directPostNode.key;
			currentNode.value = directPostNode.value;
		}
		return true;
	}

	// 方法作用为得到待删除节点的直接后继节点
	private Node getDirectPostNode(Node delNode) {
		Node parentNode = delNode;// 用来保存待删除节点的直接后继节点的父亲节点
		Node direcrPostNode = delNode;// 用来保存待删除节点的直接后继节点
		Node currentNode = delNode.rightChild;
		while (currentNode != null) {
			parentNode = direcrPostNode;
			direcrPostNode = currentNode;
			currentNode = currentNode.leftChild;
		}
		if (direcrPostNode != delNode.rightChild) {// 从树中删除此直接后继节点
			parentNode.leftChild = direcrPostNode.rightChild;
			direcrPostNode.rightChild = null;
		}
		return direcrPostNode;// 返回此直接后继节点

	}

	// 前序遍历
	public void preOrder(Node rootNode) {
		if (rootNode != null) {
			System.out.println(rootNode.key + ":" + rootNode.value);
			preOrder(rootNode.leftChild);
			preOrder(rootNode.rightChild);
		}
	}

	// 中序遍历
	public void midOrder(Node rootNode) {
		if (rootNode != null) {
			midOrder(rootNode.leftChild);
			System.out.println(rootNode.key + ":" + rootNode.value);
			midOrder(rootNode.rightChild);
		}
	}

	// 后序遍历
	public void postOrder(Node rootNode) {
		if (rootNode != null) {
			postOrder(rootNode.leftChild);
			postOrder(rootNode.rightChild);
			System.out.println(rootNode.key + ":" + rootNode.value);
		}
	}

}
