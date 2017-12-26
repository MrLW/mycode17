package com.lw.pattern.actiontype.observer.jdk;

import java.util.Observable;

/**
 * @author liwen
 * @date:2017年12月1日 上午11:19:55
 * @Function: 被观察者
 * @version 1.0
 */
public class Teacher extends Observable {

	private String info = "";

	public String getData() {
		return info;
	}

	public void setData(String data) {
		this.info = data;
		System.out.println("布置的作业为:" + data);
		setChanged();
		notifyObservers();
	}
}
