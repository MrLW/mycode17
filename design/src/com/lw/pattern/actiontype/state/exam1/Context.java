package com.lw.pattern.actiontype.state.exam1;

public class Context {

	private State state;
	
	public Context() {
		
	}

	public Context(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		System.out.println("µ±Ç°×´Ì¬£º" + state.getClass().getName());
	}

	public void request() {
		state.handle(this);
	}
}
