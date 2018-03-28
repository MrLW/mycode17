package com.lw.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		
		
		
	}

	private class ReentrantLockThread implements Runnable {

		private Lock lock;

		public ReentrantLockThread(Lock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				lock.lock();
				for (;;)
					;
			} finally {
				lock.unlock();
			}
		}

	}

}
