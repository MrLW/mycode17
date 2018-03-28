package com.lw.demo.io;

import java.nio.IntBuffer;

public class ByteBufferTest {

	public static void main(String[] args) {
		/*��ģʽ�±����ȵ���flip()����*/
		IntBuffer intBuffer = IntBuffer.allocate(2);
        intBuffer.put(12345678);
        intBuffer.put(2);
        // ������ø÷�����buffer����Ϊ��
        intBuffer.flip();
        System.err.println(intBuffer.get());
        System.err.println(intBuffer.get());
        /*��д����ģʽ��capacity��position��limitֵ*/
        IntBuffer ib = IntBuffer.allocate(10);
        ib.put(10);
        ib.put(101);
        System.err.println("Write mode: ");
        System.err.println("\tCapacity: " + ib.capacity());
        System.err.println("\tPosition: " + ib.position());
        System.err.println("\tLimit: " + ib.limit());

        ib.flip();
        System.err.println("Read mode: ");
        System.err.println("\tCapacity: " + ib.capacity());
        System.err.println("\tPosition: " + ib.position());
        System.err.println("\tLimit: " + ib.limit());
	}
}
