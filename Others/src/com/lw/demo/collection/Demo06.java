package com.lw.demo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author liwen
 * @date:2017年12月8日 上午10:16:11
 * @Function: 小练习
 * @version 1.0
 */
public class Demo06 {

	public static void main(String[] args) {
		// eq1
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list1.add("java");
		list2.add("java");
		System.out.println(list1.equals(list2)); // 集合的equals方法是比较

		// eq2
		int[] data = { 1, 2, 3 };
		// 基本类型不可以作为泛型参数,这里泛型参数是数组
		List<int[]> list3 = Arrays.asList(data);
		System.out.println("size:" + list3.size() + ";" + list3);
	}
}
