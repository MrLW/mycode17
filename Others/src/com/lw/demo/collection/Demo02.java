package com.lw.demo.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liwen
 * @date:2017年12月1日 下午2:51:06
 * @Function: LinkedList源码分析
 * @version 1.0
 */
public class Demo02 {

	public static void main(String[] args) {
		List<String> list = new LinkedList<String>(); 
		List<String> l = new ArrayList<>() ;
		list.add("java");
		list.add("android");
		l.add("c++");
		l.add("python");
		list.addAll(0,l);
		System.out.println(list);
	}
}
