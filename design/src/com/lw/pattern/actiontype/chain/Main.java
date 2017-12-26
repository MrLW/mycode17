package com.lw.pattern.actiontype.chain;
/**
 * @author liwen
 * @date:2017年11月30日 下午6:36:26
 * @Function: 责任链模式
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		Handler handler1 = new ConcreteHandler();
		Handler handler2 = new ConcreteHandler() ;
		handler1.setSuccessor(handler2);
		// 提交请求
		handler1.handle();
	}
}
