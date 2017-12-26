package com.lw.pattern.actiontype.state.exam2;

public class NoonState implements State {

	@Override
	public void doSomething(Work work) {
		if (work != null) {
			// 当在下午时
			if (work.getHour() < 13) {
				System.out.println("当前时间：" + work.getHour() + "点  犯困，午休");
			} else {
				// 否则转到下午工作状态
				work.setCurrentState(new AfterNoonState());
				work.writeProgram();
			}
		}
	}

}
