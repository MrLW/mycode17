package com.lw.demo.generic;
/**
 * @author liwen
 * @date:2017年11月24日 上午10:25:07
 * @Function: 任何运行时的代码不可执行泛型的操作
 * @version 1.0
 */
public class Demo02<T> {

	private void fun1(Object obj) {
		// if(obj instanceof T) {} error
	}
	
	private void fun2() {
//		T v = new T() ;
	}
	
}
