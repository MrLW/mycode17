package com.lw.netty.study.protocolstack.httpxml;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T> {

	private static final String CHARSET_NAME = "utf-8";
	private IBindingFactory factory;
	private StringWriter writer;
	private IMarshallingContext mctx;
	final static Charset UTF_8 = Charset.forName(CHARSET_NAME);

	@Override
	protected void encode(ChannelHandlerContext ctx, T msg, List<Object> out) throws Exception {
		factory = BindingDirectory.getFactory(msg.getClass());
		writer = new StringWriter();
		mctx = factory.createMarshallingContext();
		mctx.setIndent(2);
		mctx.marshalDocument(msg,CHARSET_NAME,null,writer);
		String xml = writer.toString();
		writer.close();
		writer = null ;
//		Unpooled.copiedBuffer(xml,)
	}

}
