package com.lw.pattern.actiontype.state.exam1;
/**
 * @author liwen
 * @date:2017��11��30�� ����5:45:17
 * @Function: ״̬ģʽ
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		Context ctx = new Context(new StateA());
		ctx.request();
	}
}
