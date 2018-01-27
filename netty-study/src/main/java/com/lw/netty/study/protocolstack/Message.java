package com.lw.netty.study.protocolstack;

import lombok.Data;

@Data
public final class Message {
	
	// 消息头
	private Header header ; 
	// 消息体
	private Object body ;
	
	@Override
	public String toString() {
		return "Message [header=" + header + ", body=" + body + "]";
	}
	
}
