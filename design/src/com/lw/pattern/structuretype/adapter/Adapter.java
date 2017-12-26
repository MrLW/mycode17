package com.lw.pattern.structuretype.adapter;

public class Adapter extends Usber implements Ps2 {

	@Override
	public void isPs2() {
		// 访问usb的接口方法
		isUsb();
	}

}
