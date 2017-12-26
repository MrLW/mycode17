package com.lw.pattern.actiontype.strategy;

public class TrainTravelStrategy implements TravelStrategy {

	@Override
	public void travel() {
		System.out.println("travel by train ...");
	}

}
