package com.lw.demo.generic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liwen
 * @date:2017��11��27�� ����10:10:04
 * @Function: ��������Ĵ�����ʽ��ע������
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
