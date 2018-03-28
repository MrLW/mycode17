package com.lw.netty.study.protocolstack;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public final class Header {
	// netty��ϢУ����,�����������:1)oxabef:�̶�ֵ,��������Ϣ��nettyЭ��Э��2���ֽ�;2)���汾��:1~255 1���ֽ�;3)�ΰ汾��:1~255 1���ֽ�
	private int crcCode = 0xabef0101 ;
	private int length ;// ��Ϣ�ܳ��� ������Ϣͷ����Ϣ��
	private long sessionID ; // �Ựid
	private byte type ;// ��Ϣ����
	private byte priority ;// ��Ϣ���ȼ�
	private Map<String, Object> attachment = new HashMap<String, Object>() ;// ����
}
