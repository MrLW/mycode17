package com.lw.netty.study.protocolstack.http;

import com.lw.netty.study.utils.ByteBufToBytes;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;

public class HttpServerHandler extends ChannelInboundHandlerAdapter {

	ByteBufToBytes bytes;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		if (msg instanceof HttpRequest) { // 如果是Http请求
			HttpRequest request = (HttpRequest) msg;
			System.out.println("msgType:" + request.headers().get("messageType"));
			System.out.println("businessType:" + request.headers().get("businessType"));
			if (HttpUtil.isContentLengthSet(request)) {
				bytes = new ByteBufToBytes((int) HttpUtil.getContentLength(request));
			}

			if (msg instanceof HttpContent) {
				HttpContent content = (HttpContent) msg;
				ByteBuf buf = content.content();
				bytes.reading(buf);
				content.release();
				if (bytes.isEnd()) {
					String resultStr = new String(bytes.readFull());
					System.out.println("Client said:" + resultStr);
					FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
							Unpooled.wrappedBuffer("I am ok".getBytes()));
					response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
					response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
					response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
					ctx.write(response);
					ctx.flush();
				}
			}
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}