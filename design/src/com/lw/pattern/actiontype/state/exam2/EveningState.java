package com.lw.pattern.actiontype.state.exam2;

public class EveningState implements State {

	@Override
	public void doSomething(Work work) {
		if (work != null) {
			// 当在下午时
			if (work.taskFinished()) {
				System.out.println("当前时间：" + work.getHour() + "点  下班了。。。");
				work.setCurrentState(new SleepingState());
				work.writeProgram();
			} else if (work.getHour() < 21) {
				System.out.println("当前时间：" + work.getHour() + "点  加班中。。。");
			}
		}
	}

}
