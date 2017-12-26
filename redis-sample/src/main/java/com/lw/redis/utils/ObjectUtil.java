package com.lw.redis.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectUtil {
	
	public static byte[] object2Bytes(Object obj) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(obj);
		byte[] bytes = bo.toByteArray();
		bo.close();
		oo.close();
		return bytes;
	}

	/**
	 * byte[]ת����
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static Object bytes2Object(byte[] bytes) throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream sIn = new ObjectInputStream(in);
		return sIn.readObject();
	}
}
