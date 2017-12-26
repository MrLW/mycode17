package com.lw.pattern.actiontype.state.exam2;

public class NoonState implements State {

	@Override
	public void doSomething(Work work) {
		if (work != null) {
			// ��������ʱ
			if (work.getHour() < 13) {
				System.out.println("��ǰʱ�䣺" + work.getHour() + "��  ����������");
			} else {
				// ����ת�����繤��״̬
				work.setCurrentState(new AfterNoonState());
				work.writeProgram();
			}
		}
	}

}
