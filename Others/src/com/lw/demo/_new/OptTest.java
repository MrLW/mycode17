package com.lw.demo._new;

import java.nio.channels.SelectionKey;

public class OptTest {

	public static void main(String[] args) {
		// 0000	1000
		// 1111 0111
		// a & 1111 0111
		Integer i = 8 ;
		SelectionKey selectionKey = null ;
		
		System.out.println((~i));
//		boolean flag = i & SelectionKey.OP_ACCEPT;
		System.out.println(1&1);
	}
}
