package com.lw.pattern.createtype.build;

/**
 * @author liwen
 * @date:2017年12月4日 上午10:27:31
 * @Function: 概念:将一个复杂对象的构建与其表示分离,使同样的构建过程可以创建不同的表示.
 * 			     适用场景:当一个对象的创建需要不同的小对象组成,将对象本身的创建分开来,通常来说这个对象可能比较复杂.
 * 			      缺点：被创建对象具有基本相同的组件.
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
