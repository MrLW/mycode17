package com.lw.pattern.actiontype.state.exam2;

public class Work {

	private int hour;// ����ʱ��
	private boolean finished;
	private State currentState;

	public Work() {
		currentState = new ForenoonState();// ����ŵ㿪ʼ�ϰ�
	}

	// ��ɹ���״̬����
	public boolean taskFinished() {
		return finished;
	}

	// ��������
	public void writeProgram() {
		currentState.doSomething(this);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
}
