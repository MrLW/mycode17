package com.lw.pattern.actiontype.state.exam1;

public class StateB implements State {

	@Override
	public void handle(Context ctx) {
		ctx.setState(new StateB());
	}

}
