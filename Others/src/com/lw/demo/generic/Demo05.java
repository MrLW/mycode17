package com.lw.demo.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Demo05 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		ParameterizedType type = (ParameterizedType) list.getClass().getGenericSuperclass();
		System.out.println(type.getActualTypeArguments()[0]);

		List<String> list2 = new ArrayList<String>() {
		};
		ParameterizedType type2 = (ParameterizedType) list2.getClass().getGenericSuperclass();
		System.out.println(type2.getActualTypeArguments()[0]);

		Sup<String> box = new Sup<String>();
		Type t = box.getClass().getGenericSuperclass();
		String typeName = t.getTypeName();
		System.out.println(typeName);
		Sub sub = new Sub();
		ParameterizedType type3 = (ParameterizedType) sub.getClass().getGenericSuperclass();
		System.out.println(type3.getActualTypeArguments()[0]);
	}

}

class Sub extends Sup<String> {
	
}

class Sup<T> {
	private T data;

	public Sup() {

	}

	public Sup(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}
}
