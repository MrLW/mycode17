package com.lw.demo.others;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestAtomicIntegerFieldUpdater {
	public static void main(String[] args) {
		TestAtomicIntegerFieldUpdater tIA = new TestAtomicIntegerFieldUpdater();
		tIA.doIt();
		System.out.println("-------");
		AtomicInteger workerState = new AtomicInteger();
		
	}

	public AtomicIntegerFieldUpdater<DataDemo> updater(String name) {
		AtomicIntegerFieldUpdater<DataDemo> newUpdater = AtomicIntegerFieldUpdater.newUpdater(DataDemo.class, name);
		return newUpdater;

	}

	public void doIt() {
		DataDemo data = new DataDemo();
		System.out.println("publicVar = " + updater("privateVar").getAndAdd(data, 2));
		/*
		 * 由于在DataDemo类中属性value2/value3,在TestAtomicIntegerFieldUpdater中不能访问
		 */
		// System.out.println("protectedVar =
		// "+updater("protectedVar").getAndAdd(data,2));
		// System.out.println("privateVar = "+updater("privateVar").getAndAdd(data,2));

		// System.out.println("staticVar =
		// "+updater("staticVar").getAndIncrement(data));//报java.lang.IllegalArgumentException
		/*
		 * 下面报异常：must be integer
		 */
		// System.out.println("integerVar =
		// "+updater("integerVar").getAndIncrement(data));
		// System.out.println("longVar = "+updater("longVar").getAndIncrement(data));
	}

}

class DataDemo {
	public volatile int publicVar = 3;
	protected volatile int protectedVar = 4;
	// 私有变量报错:
	//Class com.lw.demo.others.TestAtomicIntegerFieldUpdater can not access a member of class com.lw.demo.others.DataDemo with modifiers "private volatile"
	private volatile int privateVar = 5;
	// error:java.lang.IllegalArgumentException
	public volatile static int staticVar = 10;
	// final和volatile不能同时存在:必须是volatile
	public final  int finalVar = 11;
	// 引用类型error:Must be integer type
	public volatile Integer integerVar = 19;
	public volatile Long longVar = 18L;

}