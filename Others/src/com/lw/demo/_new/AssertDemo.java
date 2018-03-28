package com.lw.demo._new;

public class AssertDemo {

	public static void main(String[] args) {
		test1(-5);
	}

	private static void test1(int a) {
		assert a > 0 : "something goes wrong here."; 
		System.out.println(a);
	}
}
