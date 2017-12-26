package com.lw.pattern.facade;
/**
 * Facade 模式旨在对客户提供一个访问入口、接口，具体内部的 Subsystem 子系统，
 * 客户端调用时并不需要知道子系统的详细，针对不同的客户端也可以实现多个Facade来满足不同需求。
 * 注意:Facade设计模式并非一个集装箱，可以任意地放进任何多个对象。Facade模式中组件的内部应该是“相互耦合关系比较大的一系列组件”,而不是一个简单的功能集合。
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
