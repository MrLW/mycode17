package com.lw.demo.collection.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liwen
 * @date:2017��12��11�� ����10:02:35
 * @Function:ConcurrentHashMap:
 * @version 1.0
 */
public class Demo10_ConcurrentHashMap {
	
	private static final int MAXIMUM_CAPACITY = 1 << 30;

	public static void main(String[] args) {
		Map<Long, String> map = new ConcurrentHashMap<Long, String>();
		int i = 0; 
		for(i = 0 ; i < 100 ; i++ ) {
			new Thread() {
				public void run() {
					map.put(System.currentTimeMillis(), "java");
				};
			}.start();
		}
		
		// map.put(null, "c++");error
		
	}
	
	/**
	 *  ��һ������int���͵�2����Сn����
	 */
	private static final int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}
}
