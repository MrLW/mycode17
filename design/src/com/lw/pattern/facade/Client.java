package com.lw.pattern.facade;
/**
 * Facade ģʽּ�ڶԿͻ��ṩһ��������ڡ��ӿڣ������ڲ��� Subsystem ��ϵͳ��
 * �ͻ��˵���ʱ������Ҫ֪����ϵͳ����ϸ����Բ�ͬ�Ŀͻ���Ҳ����ʵ�ֶ��Facade�����㲻ͬ����
 * ע��:Facade���ģʽ����һ����װ�䣬��������طŽ��κζ������Facadeģʽ��������ڲ�Ӧ���ǡ��໥��Ϲ�ϵ�Ƚϴ��һϵ�������,������һ���򵥵Ĺ��ܼ��ϡ�
 * @author liwen
 */
public class Client {

	public static void main(String[] args) {
		ServiceA sa = new ServiceAImpl();
		ServiceB sb = new ServiceBImpl();
		sa.methodA();
		sb.methodB();
		System.out.println("=====================");
		Facade f = new Facade();
		f.methodA();
		f.methodB();
		f.methodC();
	}
}
