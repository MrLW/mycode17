package com.lw.demo.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * key和value运行null,当key重复时,后面的值覆盖前面的值
 * 
 * @author liwen
 * @date:2017年12月5日 上午11:53:55
 * @Function: HashMap:
 * @version 1.0
 */
public class Demo04 {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "java");
		map.put(2, "java");
		map.put(3, "java");
		map.put(4, "java");
		map.put(5, "java");
		map.put(6, "java");
		map.put(7, "java");
		map.put(8, "java");
		map.put(9, "java");
		map.put(10, "java");
		map.put(11, "java");
		map.put(12, "java");
		map.put(13, "java");
		map.put(14, "java");
		map.put(15, "java");
		map.put(16, "java");
		map.put(17, "java");
		map.put(18, "java");
		
		Set<Entry<Integer, String>> entrys = map.entrySet();
		System.out.println(entrys);
		
		Set<Integer> set = map.keySet();
		System.out.println(set);

		Map map2 = new HashMap();
		map2.put(null, 1);
		map2.put(null, 2); 
		map2.put(null, null); 
		System.out.println(map2.get(null));
	}
}
