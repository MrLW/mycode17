package com.lw.pattern.actiontype.observer.my;

public class Main {
	
	public static void main(String[] args) {
		// �����������
		ConcreteSubject subject = new ConcreteSubject();
		// �����۲���
		Observer observer = new ConcreteObserver();
		// ���۲��ߵǼǵ����������
		subject.attach(observer);
		// �ı������״̬
		subject.change("this is new state ");
	}
}
