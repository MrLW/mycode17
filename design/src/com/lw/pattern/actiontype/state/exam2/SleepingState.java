package com.lw.pattern.actiontype.state.exam2;

public class SleepingState implements State {

	@Override
	public void doSomething(Work work) {
		System.out.println("当前时间：" + work.getHour() + "点  睡觉了。");
	}

}
