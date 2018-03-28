package com.lw.netty.study.chapter09.marshalling;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

public class MarshallingCodeFactory {

	/**
	 * ���� Marshalling������
	 */
	public static MarshallingDecoder buildMarshallingDecoder() {
		// ��ȡjava���л���������
		MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
		MarshallingDecoder decoder = new MarshallingDecoder(provider);
		return decoder;
	}
	
	/**
	 * ���� Marshalling����
	 */
	public static MarshallingDecoder buildMarshallingEncoder() {
		// ��ȡjava���л���������
		MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
		MarshallingDecoder decoder = new MarshallingDecoder(provider);
		return decoder;
	}
	
}
