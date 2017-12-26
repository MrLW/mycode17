package com.lw.pattern.actiontype.observer.jdk;

public class Main {

	public static void main(String[] args) {
		Teacher t = new Teacher() ;
		Student zs = new Student(t, "zs");
		Student ls = new Student(t, "ls");
		Student ww = new Student(t, "ww");
		
		t.setData("第二页第五题");
		t.setData("第三页第五题");
		t.setData("第六页第五题");
	}
}
