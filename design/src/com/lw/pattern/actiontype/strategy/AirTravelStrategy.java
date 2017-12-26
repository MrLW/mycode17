package com.lw.pattern.actiontype.strategy;

public class AirTravelStrategy implements TravelStrategy {

	@Override
	public void travel() {
		System.out.println("travel by air ...");
	}

}
