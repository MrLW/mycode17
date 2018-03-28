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
		 * ������DataDemo��������value2/value3,��TestAtomicIntegerFieldUpdater�в��ܷ���
		 */
		// System.out.println("protectedVar =
		// "+updater("protectedVar").getAndAdd(data,2));
		// System.out.println("privateVar = "+updater("privateVar").getAndAdd(data,2));

		// System.out.println("staticVar =
		// "+updater("staticVar").getAndIncrement(data));//��java.lang.IllegalArgumentException
		/*
		 * ���汨�쳣��must be integer
		 */
		// System.out.println("integerVar =
		// "+updater("integerVar").getAndIncrement(data));
		// System.out.println("longVar = "+updater("longVar").getAndIncrement(data));
	}

}

class DataDemo {
	public volatile int publicVar = 3;
	protected volatile int protectedVar = 4;
	// ˽�б�������:
	//Class com.lw.demo.others.TestAtomicIntegerFieldUpdater can not access a member of class com.lw.demo.others.DataDemo with modifiers "private volatile"
	private volatile int privateVar = 5;
	// error:java.lang.IllegalArgumentException
	public volatile static int staticVar = 10;
	// final��volatile����ͬʱ����:������volatile
	public final  int finalVar = 11;
	// ��������error:Must be integer type
	public volatile Integer integerVar = 19;
	public volatile Long longVar = 18L;

}