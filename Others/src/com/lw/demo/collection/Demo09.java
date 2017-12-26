package com.lw.demo.collection;

import java.util.Iterator;

/**
 * @author liwen
 * @date:2017年12月8日 上午11:23:31
 * @Function:
 * @version 1.0
 */
public class Demo09 {

	public static void main(String[] args) {
		MyIterable iterable = new MyIterable() ;
		System.out.println(iterable);
	}
}
class MyIterable implements Iterable<String>,Iterator<String>{
	
	@Override
	public Iterator<String> iterator() {
		System.out.println("MyIterable.iterator()");
		return null;
	}

	@Override
	public boolean hasNext() {
		System.out.println("MyIterable.hasNext()");
		return false;
	}

	@Override
	public String next() {
		System.out.println("MyIterable.next()");
		return null;
	}
	
}