package com.lw.pattern.actiontype.observer.my;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

	private List<Observer> observers = new ArrayList<Observer>() ;
	
	/**
	 *  注册观察者
	 * @param observer 观察者对象
	 */
	public void attach(Observer observer) {
		observers.add(observer) ;
		System.out.println("attach an observer ");
	}
	
	/** 
	 * 删除观察者对象
	 * @param observer 观察者对象
	 */
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers(String newState) {
		for (Observer observer : observers) {
			observer.update(newState);
		}
	}
}
