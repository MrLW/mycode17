package com.lw.demo.generic;

import java.util.ArrayList;

/**
 * @author liwen
 * @date:2017��11��24�� ����10:18:52
 * @Function: ����java����
 * @version 1.0
 */
public class Demo01 {

	public static void main(String[] args) {
		Class clazz1 = new ArrayList<String>().getClass();
		Class clazz2 = new ArrayList<Integer>().getClass();
		System.out.println(clazz1 == clazz2); // true

	}
}
