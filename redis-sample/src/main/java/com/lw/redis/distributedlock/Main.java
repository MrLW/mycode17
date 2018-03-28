package com.lw.redis.distributedlock;

public class Main {

	public static void main(String[] args) {
		Service service = new Service();
		for (int i = 0; i < 50; i++) {
			ThreadA thread = new ThreadA(service);
			thread.start();
		}
	}
}
