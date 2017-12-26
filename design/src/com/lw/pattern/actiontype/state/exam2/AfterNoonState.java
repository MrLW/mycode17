package com.lw.pattern.actiontype.state.exam2;

public class AfterNoonState implements State {

	@Override
	public void doSomething(Work work) {
		if (work != null) {
			// ��������ʱ
			if (work.getHour() < 17) {
				System.out.println("��ǰʱ�䣺" + work.getHour() + "��  ����״̬����������Ŭ��");
			} else {
				// ����ת�����Ϲ���״̬
				work.setCurrentState(new EveningState());
				work.writeProgram();
			}
		}
	}

}
