package com.lw.pattern.actiontype.observer.jdk;

public class Main {

	public static void main(String[] args) {
		Teacher t = new Teacher() ;
		Student zs = new Student(t, "zs");
		Student ls = new Student(t, "ls");
		Student ww = new Student(t, "ww");
		
		t.setData("�ڶ�ҳ������");
		t.setData("����ҳ������");
		t.setData("����ҳ������");
	}
}
