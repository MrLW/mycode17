package com.lw.demo.collection.map;

import java.util.Hashtable;
import java.util.Map;
/**
 * @author liwen
 * @date:2017��12��7�� ����1:22:21
 * @Function:HashTableԴ��,Integer.MAX_VALUE == 0x7FFFFFFF
 * @version 1.0
 */
public class Test05_HashTable {

	public static void main(String[] args) {
		Map<Integer, String> table = new Hashtable<Integer, String>() ;
		table.put(1, "java");
		String v = table.put(1, "c");
		System.out.println(v + ";" + table.get(1));
//		table.put(null, "c++"); error
//		table.put(2, null); error
		int a = Integer.MAX_VALUE + 1 ;
		System.out.println(a & 0x7FFFFFFF);
		
	}
}
