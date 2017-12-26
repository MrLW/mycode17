package com.lw.protobuf;

import com.lw.protobuf.LoginRequestProto.LoginRequest;
import com.lw.protobuf.LoginRequestProto.LoginRequest.Builder;

public class TestProto {

	public static void main(String[] args) {
		Builder builder = LoginRequestProto.LoginRequest.newBuilder();
		builder.setEmail("lw@qq.com");
		builder.setName("lw");
		builder.setId(1);
		LoginRequest request = builder.build();
		byte[] bs = request.toByteArray();
		System.out.println("google protobuf size = " + bs.length);
	}
}
