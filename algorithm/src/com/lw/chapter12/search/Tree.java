package com.lw.chapter12.search;

public class Tree {

	// ����
	Node root;

	// ����
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

	// ���
	public Node minNode() {
		Node minNode = root;
		while (minNode.leftChild != null) {
			minNode = minNode.leftChild;
		}
		return minNode;
	}

	// ���
	public Node maxNode() {
		Node maxNode = root;
		while (maxNode.rightChild != null) {
			maxNode = maxNode.rightChild;
		}
		return maxNode;
	}

	// ����
	public Node insert(int key, int value) {
		// root Ϊnull�������,key+value���root
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
		// ����Node
		Node newNode = new Node(key, value);
		if (isLeftChild)
			parNode.leftChild = newNode;
		else
			parNode.rightChild = newNode;
		return parNode;
	}

	// ɾ���ڵ�(�Ƚϸ���)
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
			// Ҫɾ���Ľڵ�ΪҶ�ӽڵ�
			if (currentNode == root)
				root = null;
			else if (isLeftChild)
				parentNode.leftChild = null;
			else
				parentNode.rightChild = null;
		} else if (currentNode.rightChild == null) {// Ҫɾ���Ľڵ�ֻ������
			if (currentNode == root)
				root = currentNode.leftChild;
			else if (isLeftChild)
				parentNode.leftChild = currentNode.leftChild;
			else
				parentNode.rightChild = currentNode.leftChild;
		} else if (currentNode.leftChild == null) {// Ҫɾ���Ľڵ�ֻ���Һ���
			if (currentNode == root)
				root = currentNode.rightChild;
			else if (isLeftChild)
				parentNode.leftChild = currentNode.rightChild;
			else
				parentNode.rightChild = currentNode.rightChild;
		} else { 
			// Ҫɾ���Ľڵ�������������Һ���
			// ˼·���ô�ɾ���ڵ��������е�keyֵ��С�ڵ��ֵ�����Ҫɾ���Ľڵ��ֵ,Ȼ��ɾ����������keyֵ��С�Ľڵ�
			// ������key��С�Ľڵ�һ������������,����ɾ�����key��С�Ľڵ�һ��������Ҷ�ӽڵ����ֻ���������Ľڵ�
			Node directPostNode = getDirectPostNode(currentNode);
			currentNode.key = directPostNode.key;
			currentNode.value = directPostNode.value;
		}
		return true;
	}

	// ��������Ϊ�õ���ɾ���ڵ��ֱ�Ӻ�̽ڵ�
	private Node getDirectPostNode(Node delNode) {
		Node parentNode = delNode;// ���������ɾ���ڵ��ֱ�Ӻ�̽ڵ�ĸ��׽ڵ�
		Node direcrPostNode = delNode;// ���������ɾ���ڵ��ֱ�Ӻ�̽ڵ�
		Node currentNode = delNode.rightChild;
		while (currentNode != null) {
			parentNode = direcrPostNode;
			direcrPostNode = currentNode;
			currentNode = currentNode.leftChild;
		}
		if (direcrPostNode != delNode.rightChild) {// ������ɾ����ֱ�Ӻ�̽ڵ�
			parentNode.leftChild = direcrPostNode.rightChild;
			direcrPostNode.rightChild = null;
		}
		return direcrPostNode;// ���ش�ֱ�Ӻ�̽ڵ�

	}

	// ǰ�����
	public void preOrder(Node rootNode) {
		if (rootNode != null) {
			System.out.println(rootNode.key + ":" + rootNode.value);
			preOrder(rootNode.leftChild);
			preOrder(rootNode.rightChild);
		}
	}

	// �������
	public void midOrder(Node rootNode) {
		if (rootNode != null) {
			midOrder(rootNode.leftChild);
			System.out.println(rootNode.key + ":" + rootNode.value);
			midOrder(rootNode.rightChild);
		}
	}

	// �������
	public void postOrder(Node rootNode) {
		if (rootNode != null) {
			postOrder(rootNode.leftChild);
			postOrder(rootNode.rightChild);
			System.out.println(rootNode.key + ":" + rootNode.value);
		}
	}

}
