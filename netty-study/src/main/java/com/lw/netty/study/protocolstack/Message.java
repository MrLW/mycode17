package com.lw.netty.study.protocolstack;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public final class Message {
	// ��Ϣͷ
	private Header header ; 
	// ��Ϣ��
	private Object body ;
}
