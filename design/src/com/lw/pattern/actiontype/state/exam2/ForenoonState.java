package com.lw.pattern.actiontype.state.exam2;

public class ForenoonState implements State {

	@Override
	public void doSomething(Work work) {
		if (work != null) {
			// 当在上午工作时
			if (work.getHour() < 12) {
				System.out.println("当前时间：" + work.getHour() + "点  上午工作，精神百倍");
			} else {
				// 否则转到下午工作状态
				work.setCurrentState(new NoonState());
				work.writeProgram();
			}
		}
	}

}
