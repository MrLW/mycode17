package com.lw.pattern.actiontype.state.exam2;

public class SleepingState implements State {

	@Override
	public void doSomething(Work work) {
		System.out.println("��ǰʱ�䣺" + work.getHour() + "��  ˯���ˡ�");
	}

}
