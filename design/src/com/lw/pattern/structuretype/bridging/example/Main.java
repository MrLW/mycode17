package com.lw.pattern.structuretype.bridging.example;

/**
 *  �ο�����:https://www.cnblogs.com/lfxiao/p/6815760.html
 * @author liwen
 * @date:2017��12��4�� ����6:09:24
 * @Function: ����˼��:�����󲿷�������ʵ�ֲ��ַ��룬ʹ���Ƕ����Զ����ر仯������һ�ֶ���ṹ��ģʽ���ֳ�Ϊ����(Handle and Body)ģʽ��ӿ�(Interface)ģʽ
 * 			      �������:�����һ��ά�ȵı仯����Ӱ�쵽��һ��ά��Ҳ���ű仯.
 * 			      ʵ�ַ�ʽ:��ʵ��ά��ʹ�ýӿڱ�ʾ,���ڳ���ά��,ʹ�ó�����,�����������ýӿ�ʵ��.
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		AreaA a = new AreaA2();
		a.qiao = new AreaB3();
		a.fromAreaA();
		a.qiao.targetAreaB();
	}
}
