package com.lw.demo.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author liwen
 * @date:2017年12月20日 下午1:05:10
 * @Function:ArrayDeque
 * 			
 * @version 1.0
 */
public class Test03_ArrayDeque {

	public static void main(String[] args) {
		Deque<String> deque = new ArrayDeque<String>();
		deque.add("java");
		deque.offer("c");
		System.out.println(deque);
	}
}
