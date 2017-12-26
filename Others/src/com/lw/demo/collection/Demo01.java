package com.lw.demo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author liwen
 * @date:2017��12��1�� ����1:54:53
 * @Function:ArrayListԴ��ʵ��
 * 1������������ArrayList,��new������ʱ��Ĭ��elementData�ĳ���Ϊ0
 *   ����ӵ�ʱ���һ������(grow()����)���ʼ������ΪDEFAULT_CAPACITY(10)
 *   �ĳ���,�˺����Ҫ���ݵĻ����ǰ�1.5����������
 * 2����������ArrayList,�����ǲ���������DEFAULT_CAPACITY��һ��������ֵ
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
