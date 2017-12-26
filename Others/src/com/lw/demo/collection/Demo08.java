package com.lw.demo.collection;

import java.util.AbstractSet;
import java.util.Iterator;

/**
 * @author liwen
 * @date:2017年12月8日 上午11:00:49
 * @Function: 演示 AbstractSet
 * @version 1.0
 */
public class Demo08 {

	public static void main(String[] args) {
		MyAbstractSet set = new MyAbstractSet();
		System.out.println(set);
	}
}

class MyAbstractSet extends AbstractSet<String> {

	@Override
	public Iterator<String> iterator() {
		System.out.println("iteraotr method execute ...");
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

}
