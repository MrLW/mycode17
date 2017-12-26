package com.lw.pattern.actiontype.chain;

public class ConcreteHandler extends Handler {

	@Override
	public void handle() {
		if (getSuccessor() != null) {
			System.out.println("放过请求");
			getSuccessor().handle();
		} else {
			System.out.println("处理请求");
		}
	}

}
