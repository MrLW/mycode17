package com.lw.demo.io;

import java.nio.IntBuffer;

public class ByteBufferTest {

	public static void main(String[] args) {
		/*读模式下必须先调用flip()方法*/
		IntBuffer intBuffer = IntBuffer.allocate(2);
        intBuffer.put(12345678);
        intBuffer.put(2);
        // 必须调用该方法将buffer设置为读
        intBuffer.flip();
        System.err.println(intBuffer.get());
        System.err.println(intBuffer.get());
        /*读写两种模式下capacity、position、limit值*/
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
