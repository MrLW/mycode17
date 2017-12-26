package com.lw.pattern.actiontype.state.exam1;
/**
 * @author liwen
 * @date:2017年11月30日 下午5:45:17
 * @Function: 状态模式
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		Context ctx = new Context(new StateA());
		ctx.request();
	}
}
