package com.lw.pattern.actiontype.chain;
/**
 * @author liwen
 * @date:2017��11��30�� ����6:36:26
 * @Function: ������ģʽ
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		Handler handler1 = new ConcreteHandler();
		Handler handler2 = new ConcreteHandler() ;
		handler1.setSuccessor(handler2);
		// �ύ����
		handler1.handle();
	}
}
