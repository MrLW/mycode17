package com.lw.pattern.actiontype.strategy;

public class Main {

	public static void main(String[] args) {
		AirTravelStrategy strategy = new AirTravelStrategy();
		Context context = new Context(strategy);
		context.setStrategy(new BicycleTravelStrategy());
		context.travel();
	}
}
