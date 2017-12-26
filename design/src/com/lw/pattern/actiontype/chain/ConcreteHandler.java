package com.lw.pattern.actiontype.chain;

public class ConcreteHandler extends Handler {

	@Override
	public void handle() {
		if (getSuccessor() != null) {
			System.out.println("�Ź�����");
			getSuccessor().handle();
		} else {
			System.out.println("��������");
		}
	}

}
