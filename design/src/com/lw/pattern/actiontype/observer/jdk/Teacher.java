package com.lw.pattern.actiontype.observer.jdk;

import java.util.Observable;

/**
 * @author liwen
 * @date:2017��12��1�� ����11:19:55
 * @Function: ���۲���
 * @version 1.0
 */
public class Teacher extends Observable {

	private String info = "";

	public String getData() {
		return info;
	}

	public void setData(String data) {
		this.info = data;
		System.out.println("���õ���ҵΪ:" + data);
		setChanged();
		notifyObservers();
	}
}
