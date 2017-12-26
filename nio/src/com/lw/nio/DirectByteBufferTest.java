package com.lw.nio;

import java.nio.ByteBuffer;

/**
 * @author liwen
 * @date:2017��12��15�� ����10:57:04
 * @Function: ����javaֱ���ڴ�
 * 			  DirectByteBuffer�Ķ�д�ٶ� > ��ͨByteBuffer
 * 			  DirectByteBuffer�Ĵ��������ٶ� < ��ͨByteBuffer�Ĵ��������ٶ�
 * @version 1.0
 */
public class DirectByteBufferTest {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		ByteBuffer buffer = ByteBuffer.allocate(1024);
//		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		
		for (int i = 0; i < 1000000; i++) {
			for (int j = 0; j < 100; j++) {
				buffer.putInt(j); // ��DirectBufferд������
			}
			buffer.flip();
			for (int j = 0; j < 100; j++) {
				buffer.get(); // ��DirectBuffer�ж�ȡ����
			}
			buffer.clear();
		}
		System.out.println("DirectBuffer use : " + (System.currentTimeMillis() - start) + "ms");
		
	}
}
