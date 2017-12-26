package com.lw.pattern.actiontype.strategy;

public class BicycleTravelStrategy implements TravelStrategy {

	@Override
	public void travel() {
		System.out.println("travel by bicycle ...");
	}

}
