package com.lw.netty.study.protocolstack;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
@Data
public final class Header {
	// netty消息校验码,由三部分组成:1)oxabef:固定值,表明该消息时netty协议协议2个字节;2)主版本号:1~255 1个字节;3)次版本号:1~255 1个字节
	private int crcCode = 0xabef0101 ;
	private int length ;// 消息总长度 包括消息头和消息体
	private long sessionID ; // 会话id
	private byte type ;// 消息类型
	private byte priority ;// 消息优先级
	private Map<String, Object> attachment = new HashMap<String, Object>() ;// 附件
	@Override
	public String toString() {
		return "Header [crcCode=" + crcCode + ", length=" + length + ", sessionID=" + sessionID + ", type=" + type
				+ ", priority=" + priority + ", attachment=" + attachment + "]";
	}
	
}
