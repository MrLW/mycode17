package com.lw.pattern.actiontype.state.exam2;

public class AfterNoonState implements State {

	@Override
	public void doSomething(Work work) {
		if (work != null) {
			// 当在下午时
			if (work.getHour() < 17) {
				System.out.println("当前时间：" + work.getHour() + "点  下午状态还不错，继续努力");
			} else {
				// 否则转到晚上工作状态
				work.setCurrentState(new EveningState());
				work.writeProgram();
			}
		}
	}

}
