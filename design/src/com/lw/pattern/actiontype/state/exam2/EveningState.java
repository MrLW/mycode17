package com.lw.pattern.actiontype.state.exam2;

public class EveningState implements State {

	@Override
	public void doSomething(Work work) {
		if (work != null) {
			// ��������ʱ
			if (work.taskFinished()) {
				System.out.println("��ǰʱ�䣺" + work.getHour() + "��  �°��ˡ�����");
				work.setCurrentState(new SleepingState());
				work.writeProgram();
			} else if (work.getHour() < 21) {
				System.out.println("��ǰʱ�䣺" + work.getHour() + "��  �Ӱ��С�����");
			}
		}
	}

}
