package com.lw.pattern.createtype.prototype;

/**
 * @author liwen
 * @date:2017年12月4日 上午10:18:49
 * @Function: 注意:使用原型模式不会调用类的构造函数,因此它和单例是冲突的,因为它是无视构造函数的
 *            涉及到的问题:Cloneable,浅拷贝/深拷贝、浅拷贝带来的问题(copy的引用类型修改了会导致src也被修改,因为指向同一个对象).
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		ConcretePrototype prototype1 = new ConcretePrototype();
		prototype1.name = "lw"; 
		for (int i = 0; i < 10; i++) {
			ConcretePrototype clone = (ConcretePrototype) prototype1.clone();
			System.out.println(clone.list);
			System.out.println(clone.name);
		}

	}
}
