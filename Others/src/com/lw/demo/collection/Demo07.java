package com.lw.demo.collection;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author liwen
 * @date:2017年12月8日 上午10:48:16
 * @Function: HashMap.keySet底层实现
 * @version 1.0
 */
public class Demo07 {

	public static void main(String[] args) {

		MyIterator t = new MyIterator();
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(1);
		result.add(2);
		result.add(3);
		Set<Integer> set = t.keySet(result);
		Set<Integer> set_copy = null;
		// 只要使用就会调用iterator()
		set_copy = set;
		System.out.println(set_copy); // 此时同样会调用iterator()方法
	}
}

class MyIterator {

	public Set<Integer> keySet(List<Integer> result) {

		Set<Integer> keySet = new AbstractSet<Integer>() {

			public Iterator<Integer> iterator() {
				System.out.println("iterator method execute ... ");
				return new Iterator<Integer>() {
					// result模拟HashMap中的keys
					private Iterator<Integer> i = result.iterator();

					@Override
					public boolean hasNext() {
						return i.hasNext();
					}

					@Override
					public Integer next() {
						return i.next();
					}

					@Override
					public void remove() {
						i.remove();
					}
				};
			}

			@Override
			public int size() {
				return 0;
			}
		};

		return keySet;
	}
}
