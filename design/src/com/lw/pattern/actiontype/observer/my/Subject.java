package com.lw.pattern.actiontype.observer.my;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

	private List<Observer> observers = new ArrayList<Observer>() ;
	
	/**
	 *  ע��۲���
	 * @param observer �۲��߶���
	 */
	public void attach(Observer observer) {
		observers.add(observer) ;
		System.out.println("attach an observer ");
	}
	
	/** 
	 * ɾ���۲��߶���
	 * @param observer �۲��߶���
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
