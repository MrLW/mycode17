package com.lw.demo.generic;

import java.util.ArrayList;
import java.util.List;

public class Demo04 {

	public static void main(String[] args) {
		// List<Object> objs = new ArrayList<Long>() ; error
		Object[] objs = new Long[10]; // rigth , running check
		objs[1] = "hello";
	}
}
