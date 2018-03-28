package com.lw.netty.study.protocolstack;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author liwen
 * @date:2018年1月17日 下午2:44:01
 * @Function: 支持自动TCP粘包和半包处理
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
		// 调用父类解码器返回之后,要么是整包消息,要么消息为空
		// 如果为null,则是一个半包消息
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null) {
			return null;
		}
		return frame;
	}

}
