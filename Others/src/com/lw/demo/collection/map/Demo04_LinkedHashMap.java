package com.lw.demo.collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class Demo04_LinkedHashMap {

	public static void main(String[] args) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < 10; i++) {// ��˳�����1~9
			map.put(i, i);
		}
		System.out.println("ԭ���ݣ�" + map.toString());
		map.get(3);
		System.out.println("��ѯ���ڵ�ĳһ����" + map.toString());
		map.put(4, 4);
		System.out.println("�����Ѵ��ڵ�ĳһ����" + map.toString()); // ֱ�ӵ����Ѵ��ڵ�toString��������Ȼ�Լ���Ҫ�õ�����ʵ��
		map.put(10, 10);
		System.out.println("����һ��ԭ��û���ڵģ�" + map.toString());
	}
}
