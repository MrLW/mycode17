package com.lw.demo.binning;
/**
 * @author liwen
 * @date:2017年12月7日 上午11:56:25
 * @Function: 验证自动装箱拆箱
 * @version 1.0
 */
public class AutoBinning {

	public static void main(String[] args) {
		Integer i = 128 ; // 装箱
		Integer j = 128 ;
		Integer x = 1 ; 	// 装箱
		Integer y = 1 ;
		
		System.out.println(i == j);
		System.out.println(x == y);
	}
}
