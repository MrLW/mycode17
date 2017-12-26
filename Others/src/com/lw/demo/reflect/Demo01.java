package com.lw.demo.reflect;

import java.lang.reflect.Constructor;

/**
 * @author liwen
 * @date:2017年11月28日 下午2:44:19
 * @Function: 测试反射是否执行constructor  method
 * @version 1.0 
 */
public class Demo01 {

	public Demo01() {
		System.out.println("constructor method execute");
	}
	
	public Demo01(String param) {
		System.out.println("constructor method with param execute,param is : " + param );
	}

	public static void main(String[] args) throws Exception {
		Class clazz = Demo01.class;
		Constructor constructor = clazz.getConstructor(String.class);
		Object obj = constructor.newInstance("hello reflect");
		System.out.println(obj);
	}
}
