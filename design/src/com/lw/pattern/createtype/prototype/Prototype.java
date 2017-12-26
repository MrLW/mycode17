package com.lw.pattern.createtype.prototype;

import java.util.ArrayList;
import java.util.List;

public class Prototype implements Cloneable{
	
	public String name ;
	
	public List<String> list = new ArrayList<>() ;
	
	// 最好修改成public
	@Override
	public Prototype clone() throws CloneNotSupportedException {
		Prototype prototype = null ;
		prototype = (Prototype) super.clone();
		prototype.list = (List<String>) ((ArrayList) this.list).clone();    
		return prototype;
	}
}
