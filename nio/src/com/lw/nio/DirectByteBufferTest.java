package com.lw.nio;

import java.nio.ByteBuffer;

/**
 * @author liwen
 * @date:2017年12月15日 上午10:57:04
 * @Function: 测试java直接内存
 * 			  DirectByteBuffer的读写速度 > 普通ByteBuffer
 * 			  DirectByteBuffer的创建销毁速度 < 普通ByteBuffer的创建销毁速度
 * @version 1.0
 */
public class DirectByteBufferTest {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		ByteBuffer buffer = ByteBuffer.allocate(1024);
//		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		
		for (int i = 0; i < 1000000; i++) {
			for (int j = 0; j < 100; j++) {
				buffer.putInt(j); // 向DirectBuffer写入数据
			}
			buffer.flip();
			for (int j = 0; j < 100; j++) {
				buffer.get(); // 从DirectBuffer中读取数据
			}
			buffer.clear();
		}
		System.out.println("DirectBuffer use : " + (System.currentTimeMillis() - start) + "ms");
		
	}
}
