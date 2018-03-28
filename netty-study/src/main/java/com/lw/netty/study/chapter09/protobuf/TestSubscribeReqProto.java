package com.lw.netty.study.chapter09.protobuf;

import java.util.ArrayList;
import java.util.List;

import com.lw.netty.study.chapter09.protobuf.SubscribeReqProto.SubscribeReq;
import com.lw.netty.study.chapter09.protobuf.SubscribeReqProto.SubscribeReq.Builder;

public class TestSubscribeReqProto {

	private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
		return req.toByteArray();
	}

	private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws Exception {
		return SubscribeReqProto.SubscribeReq.parseFrom(body);
	}

	private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
		Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqId(1);
		builder.setUsername("LiWen");
		builder.setProductName("Netty Book");
		List<String> address = new ArrayList<String>();
		address.add("AnHui");
		address.add("anqin");
		address.add("huaining");
		builder.addAllAddress(address);
		SubscribeReq subscribeReq = builder.build();
		return subscribeReq;
	}

	public static void main(String[] args) throws Exception {
		SubscribeReq req = createSubscribeReq();
		System.out.println("Before Encode:" + req.toString());
		System.out.println("Encode:" + encode(req));
		System.out.println("Decode:" + decode(encode(req)));

	}
}
