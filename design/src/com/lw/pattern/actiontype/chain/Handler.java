package com.lw.pattern.actiontype.chain;

public abstract class Handler {
	
	// ���
	protected Handler successor ;
	
	public abstract void handle() ;

	public Handler getSuccessor() {
		return successor;
	}

	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	
	
}
