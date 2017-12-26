package com.lw.demo.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liwen
 * @date:2017年12月4日 上午11:25:47
 * @Function:
 * @version 1.0
 */
public class Demo03 {

	public static void main(String[] args) {
		List<String> list = new CopyOnWriteArrayList<String>() ;
		list.add("java");
		list.add("c");
		list.add("c#");
	}
}
