package com.lw.netty.study.chapter09._2;

import com.lw.netty.study.chapter09.protobuf.SubscribeReqProto;
import com.lw.netty.study.chapter09.protobuf.SubscribeReqProto.SubscribeReq.Builder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SubReqClientHandler extends SimpleChannelInboundHandler {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		for(int i = 1 ; i <= 5 ;i++) {
			ctx.writeAndFlush(subReq(i));
		}
		
	}
	
	private SubscribeReqProto.SubscribeReq subReq(int i ){
		Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqId(i);
		builder.setUsername("LiWen");
		builder.setProductName("NettyBook");
		builder.getAddressList().add("AnHui");
		builder.getAddressList().add("AnQing");
		builder.getAddressList().add("HuaiNing");
		return builder.build();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println();
	}
}
