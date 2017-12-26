package com.lw.demo.trycatch;
/**
 * @author liwen
 * @date:2017��12��4�� ����11:48:04
 * @Function: ����try/finally + return ִ��˳��
 * @version 1.0
 */
public class Demo01 {

	public static void main(String[] args) {
		int result = add(2, 3);
		System.out.println(result); 
	}

	public static int add(int i, int j) {
		int result;
		try {
			result = i + j;
			return test(result);
		} finally {
			result = i - j;
			test(result);
		}
	}

	private static int test(int result) {
		result = result * 1 ;
		return 0;
	}
}
