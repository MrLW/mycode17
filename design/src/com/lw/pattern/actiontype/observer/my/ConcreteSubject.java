package com.lw.pattern.actiontype.observer.my;

public class ConcreteSubject extends Subject {

	private String state;

	public String getState() {
		return state;
	}

	public void change(String newState) {
		state = newState;
		System.out.println("当前主题的状态:" + this.state);
		this.notifyObservers(state);
	}
}
