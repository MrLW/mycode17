package com.lw.pattern.createtype.build;

/**
 * @author liwen
 * @date:2017��12��4�� ����10:27:31
 * @Function: ����:��һ�����Ӷ���Ĺ��������ʾ����,ʹͬ���Ĺ������̿��Դ�����ͬ�ı�ʾ.
 * 			     ���ó���:��һ������Ĵ�����Ҫ��ͬ��С�������,��������Ĵ����ֿ���,ͨ����˵���������ܱȽϸ���.
 * 			      ȱ�㣺������������л�����ͬ�����.
 * 					
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		CarDirector director = new CarDirector(new CarBuilder());
		Car car = director.constructCar();
		System.out.println(car);
	}
}
