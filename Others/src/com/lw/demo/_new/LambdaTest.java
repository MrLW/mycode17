package com.lw.demo._new;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author liwen
 * @date:2017��12��19�� ����10:30:21
 * @Function:Lambda ���ʽ����
 * @version 1.0
 */
public class LambdaTest {

	public static void main(String[] args) {
//		test02();
		test03();
	}

	private static void test03() {
		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
	}

	/**
	 * ��򵥵���ʽ
	 */
	@Test
	public void test01() {
		Arrays.asList("a", "b", "c").forEach(e -> System.out.print(e));
		System.out.println();
		Arrays.asList("a", "b", "c").forEach((String e) -> System.out.print(e));
		System.out.println();
		
		
	}
	// ��ʱseparator����ʽת��Ϊfinal
	private static void test02() {
		String separator = ","; 
		Arrays.asList("a","b","c").forEach((String e) -> System.out.print(e+separator));
	}
}
