package com.lw.pattern.actiontype.state.exam1;

public class StateA implements State {

	@Override
	public void handle(Context ctx) {
		ctx.setState(new StateA());
	}

}
