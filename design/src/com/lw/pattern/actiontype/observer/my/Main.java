package com.lw.pattern.actiontype.observer.my;

public class Main {
	
	public static void main(String[] args) {
		// 创建主题对象
		ConcreteSubject subject = new ConcreteSubject();
		// 创建观察者
		Observer observer = new ConcreteObserver();
		// 将观察者登记到主题对象上
		subject.attach(observer);
		// 改变主题的状态
		subject.change("this is new state ");
	}
}
