package com.lw.netty.study.protocolstack;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
@Data
public final class Header {
	// netty��ϢУ����,�����������:1)oxabef:�̶�ֵ,��������ϢʱnettyЭ��Э��2���ֽ�;2)���汾��:1~255 1���ֽ�;3)�ΰ汾��:1~255 1���ֽ�
	private int crcCode = 0xabef0101 ;
	private int length ;// ��Ϣ�ܳ��� ������Ϣͷ����Ϣ��
	private long sessionID ; // �Ựid
	private byte type ;// ��Ϣ����
	private byte priority ;// ��Ϣ���ȼ�
	private Map<String, Object> attachment = new HashMap<String, Object>() ;// ����
	@Override
	public String toString() {
		return "Header [crcCode=" + crcCode + ", length=" + length + ", sessionID=" + sessionID + ", type=" + type
				+ ", priority=" + priority + ", attachment=" + attachment + "]";
	}
	
}
