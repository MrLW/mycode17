package com.lw.demo.thread;

public class Test {

	private static int a = 0 ;
	
	public static void setA(int a) {
		Test.a = a;
	}
	
	public static int getA() {
		return a;
	}
	
	public static void main(String[] args) {
		
		
		new Thread() {
			public void run() {
				setA(3);
			};
		}.start();
		
		new Thread() {
			public void run() {
				System.out.println(getA());
			};
		}.start();
		
	}
}
