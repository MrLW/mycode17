package com.lw.searchtree;

public class BinarySearchTreeApp {

	public static void main(String[] args) {
		Tree tree = new Tree() ;
		tree.insert(6, 6) ;
		tree.insert(3, 3) ;
		tree.insert(14, 14); 

		tree.insert(16, 16); 

		tree.insert(10, 10); 

		tree.insert(9, 9); 

		tree.insert(13, 13); 

		tree.insert(11, 11); 

		tree.insert(12, 12); 
		
		// 中序遍历
		tree.midOrder(tree.root);
		
		System.out.println("最大:" + tree.maxNode().key + ":" + tree.maxNode().value);
		System.out.println("最小:" + tree.minNode().key + ":" + tree.minNode().value);
		tree.delete(10);
	}
}
