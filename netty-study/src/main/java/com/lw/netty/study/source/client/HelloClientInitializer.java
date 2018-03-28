package com.lw.netty.study.source.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 * 
 * @author liwen
 *
 */
public class HelloClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {


        ChannelPipeline pipeline = ch.pipeline();
        
        // Add the text line codec combination first,
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        // 添加编码和解码的类
    	pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new StringEncoder());
        
        // 添加处理业务的类
        pipeline.addLast(new HelloClientHandler());

    
		
		/*
		
//		 * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
//		 * 
//		 * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
//		 * 
		 
		// 以\r或者\r\n作为分割符作为消息的分割符
		// pipeline.addLast(new DelimiterBasedFrameDecoder(512,
		// Delimiters.lineDelimiter()));
		// 解决拆包,粘包
//		pipeline.addLast("framerDecoder", new LengthFieldBasedFrameDecoder(4096, 0, 4, 0, 4));
//		pipeline.addLast("framerEncoder", new LengthFieldPrepender(4, false));

		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new StringEncoder());
//		pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
//		pipeline.addLast("encoder", new ObjectEncoder());
		// 客户端的逻辑
		pipeline.addLast("handler", new HelloClientHandler());
		
		*/
	}
}
