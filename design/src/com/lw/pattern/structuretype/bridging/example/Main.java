package com.lw.pattern.structuretype.bridging.example;

/**
 *  参考博客:https://www.cnblogs.com/lfxiao/p/6815760.html
 * @author liwen
 * @date:2017年12月4日 下午6:09:24
 * @Function: 核心思想:将抽象部分与它的实现部分分离，使它们都可以独立地变化。它是一种对象结构型模式，又称为柄体(Handle and Body)模式或接口(Interface)模式
 * 			      解决问题:解决当一个维度的变化不会影响到另一个维度也跟着变化.
 * 			      实现方式:将实现维度使用接口表示,对于抽象维度,使用抽象类,抽象类中引用接口实现.
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
