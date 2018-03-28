package com.lw.netty.study.protocolstack;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author liwen
 * @date:2018��1��17�� ����2:44:01
 * @Function: ֧���Զ�TCPճ���Ͱ������
 * @version 1.0
 */
public class MessageDecoder extends LengthFieldBasedFrameDecoder {

	public MessageDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
			int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
		super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip,
				failFast);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		// ���ø������������֮��,Ҫô��������Ϣ,Ҫô��ϢΪ��
		// ���Ϊnull,����һ�������Ϣ
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null) {
			return null;
		}
		return frame;
	}

}
