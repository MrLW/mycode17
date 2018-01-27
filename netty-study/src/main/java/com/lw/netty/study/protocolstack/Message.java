package com.lw.netty.study.protocolstack;

import lombok.Data;

@Data
public final class Message {
	
	// ��Ϣͷ
	private Header header ; 
	// ��Ϣ��
	private Object body ;
	
	@Override
	public String toString() {
		return "Message [header=" + header + ", body=" + body + "]";
	}
	
}
