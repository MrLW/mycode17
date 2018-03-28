package com.lw.netty.study.protocolstack;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public final class Message {
	// 消息头
	private Header header ; 
	// 消息体
	private Object body ;
}
