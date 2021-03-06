package com.test;

public class Main {
	static final int MAXIMUM_CAPACITY = 1 << 30;

	public static void main(String[] args) {

		int a = 16;
		System.out.println(17 & (a - 1));
		System.out.println(tableSizeFor(100));
	}

	static final int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}
}
