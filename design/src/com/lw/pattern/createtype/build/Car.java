package com.lw.pattern.createtype.build;

public class Car {
	public String engine;
	public String tyre;
	public String seat;

	public Car() {

	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getTyre() {
		return tyre;
	}

	public void setTyre(String tyre) {
		this.tyre = tyre;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Car [engine=" + engine + ", tyre=" + tyre + ", seat=" + seat + "]";
	}

}
