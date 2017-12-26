package com.lw.pattern.actiontype.observer.my;

public class ConcreteSubject extends Subject {

	private String state;

	public String getState() {
		return state;
	}

	public void change(String newState) {
		state = newState;
		System.out.println("��ǰ�����״̬:" + this.state);
		this.notifyObservers(state);
	}
}
