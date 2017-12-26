package com.lw.pattern.actiontype.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class Student implements Observer{

	private Observable observable;
	private String name ;
	
	public Student(Observable observable, String name) {
		this.observable = observable;
		this.name = name;
		observable.addObserver(this);
	}



	@Override
	public void update(Observable o, Object obj) {
		Teacher t = (Teacher) o ;
		System.out.println(this.name + "接收到的作业为:" + t.getData());
	}

	
}
