package com.lw.pattern.actiontype.state.exam2;

public class Work {

	private int hour;// 工作时间
	private boolean finished;
	private State currentState;

	public Work() {
		currentState = new ForenoonState();// 上午九点开始上班
	}

	// 完成工作状态设置
	public boolean taskFinished() {
		return finished;
	}

	// 工作内容
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
