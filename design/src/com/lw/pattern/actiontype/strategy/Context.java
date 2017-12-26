package com.lw.pattern.actiontype.strategy;

public class Context {

	private TravelStrategy strategy;

	public Context(TravelStrategy strategy) {
		this.strategy = strategy;
	}

	public void setStrategy(TravelStrategy strategy) {
		this.strategy = strategy;
	}

	public void travel() {
		strategy.travel();
	}
}
