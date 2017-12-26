package com.lw.demo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author liwen
 * @date:2017��12��8�� ����10:16:11
 * @Function: С��ϰ
 * @version 1.0
 */
public class Demo06 {

	public static void main(String[] args) {
		// eq1
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list1.add("java");
		list2.add("java");
		System.out.println(list1.equals(list2)); // ���ϵ�equals�����ǱȽ�

		// eq2
		int[] data = { 1, 2, 3 };
		// �������Ͳ�������Ϊ���Ͳ���,���ﷺ�Ͳ���������
		List<int[]> list3 = Arrays.asList(data);
		System.out.println("size:" + list3.size() + ";" + list3);
	}
}
