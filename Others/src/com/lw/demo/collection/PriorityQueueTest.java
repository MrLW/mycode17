package com.lw.demo.collection;

import java.util.PriorityQueue;
/**
 * @author liwen
 * @date:2017��12��19�� ����6:21:38
 * @Function:
 * @version 1.0
 */
public class PriorityQueueTest {

	public static void main(String[] args) {
		PriorityQueue<String> queue = new PriorityQueue<>(7) ;
		queue.add("test");
		queue.add("sfa");
		queue.add("asdf");
		System.out.println(queue.poll());
	}
}
