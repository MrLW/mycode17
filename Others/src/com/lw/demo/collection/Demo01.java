package com.lw.demo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author liwen
 * @date:2017年12月1日 下午1:54:53
 * @Function:ArrayList源码实现
 * 1、不带参数的ArrayList,在new创建的时候默认elementData的长度为0
 *   在添加的时候第一次扩容(grow()方法)会初始化长度为DEFAULT_CAPACITY(10)
 *   的长度,此后如果要扩容的话就是按1.5倍进行扩容
 * 2、带参数的ArrayList,给就是不带参数的DEFAULT_CAPACITY给一个给定的值
 * @version 1.0
 */
public class Demo01 {
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("c");
		list.add("c#");
		
		list.remove(0);
	}
	
	@Test
	public void testArrayCopy() {
		int[] a = new int[2];
		a[0] = 1 ;
		a[1] = 1 ;
		int[] b = Arrays.copyOf(a, 3);
		for (int i : b) {
			System.out.println("i:" + i );
		}
	}
}
