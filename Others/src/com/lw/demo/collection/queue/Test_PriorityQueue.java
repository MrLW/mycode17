package com.lw.demo.collection.queue;

import java.util.PriorityQueue;


/**
 * @author liwen
 * @date:2018年2月9日 下午6:25:44
 * @Function:优先队列
 * @version 1.0
 */
public class Test_PriorityQueue {

	public static void main(String[] args) {
		PriorityQueue<Person> queue = new PriorityQueue<Person>();
		queue.add(new Person(30));
		queue.add(new Person(2));
		queue.add(new Person(4));
		System.out.println(queue.poll());
	}
	
	static class Person implements Comparable<Person>{
		int age ;
		public Person(int age) {
			this.age = age ;
		}
		@Override
		public int compareTo(Person o) {
			return this.age-o.age;
		}
		@Override
		public String toString() {
			return "Person [age=" + age + "]";
		}
	}
}
