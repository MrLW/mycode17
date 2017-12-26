package com.lw.demo.generic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liwen
 * @date:2017年11月27日 上午10:10:04
 * @Function: 泛型数组的创建方式和注意事项
 * @version 1.0
 */
public class Demo03 {

	public static void main(String[] args) {
		// List<String>[] lsa = new ArrayList<String>[10] ;//error
		List<?>[] lsa2 = new ArrayList<?>[10];
		System.out.println(lsa2);
		Object obj = Array.newInstance(Integer.class, 10);
		System.out.println(obj);
	}
}
