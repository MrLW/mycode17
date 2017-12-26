package com.lw.pattern.actiontype.state.exam2;

public class ForenoonState implements State {

	@Override
	public void doSomething(Work work) {
		if (work != null) {
			// �������繤��ʱ
			if (work.getHour() < 12) {
				System.out.println("��ǰʱ�䣺" + work.getHour() + "��  ���繤��������ٱ�");
			} else {
				// ����ת�����繤��״̬
				work.setCurrentState(new NoonState());
				work.writeProgram();
			}
		}
	}

}
