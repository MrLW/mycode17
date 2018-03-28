package com.lw.demo.classloading;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class Main {

	static class MyClassLoader extends ClassLoader {
		private String classPath;

		public MyClassLoader(String classPath) {
			this.classPath = classPath;
		}

		private byte[] loadByte(String name) throws Exception {
			name = name.replaceAll("\\.", "/");
			FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
			int len = fis.available();
			byte[] data = new byte[len];
			fis.read(data);
			fis.close();
			return data;

		}

		protected Class<?> findClass(String name) throws ClassNotFoundException {
			try {
				byte[] data = loadByte(name);
				return defineClass(name, data, 0, data.length);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ClassNotFoundException();
			}
		}

	};

	/**
	 * ���Test�ļ��ڱ���Ŀ,��ʹ�õ���ϵͳ������:(sun.misc.Launcher$AppClassLoader@2a139a55)
	 * ���������Ŀû��Test�ļ�,��ʹ���Զ����������(com.lw.demo.classloading.Main$MyClassLoader@42a57993)
	 */
	public static void main(String args[]) throws Exception {
		MyClassLoader classLoader1 = new MyClassLoader("D:\\");
		Class clazz = classLoader1.loadClass("com.lw.demo.classloading.Test");
		Object obj = clazz.newInstance();
		
		
		MyClassLoader classLoader2 = new MyClassLoader("D:\\");
		Class clazz2 = classLoader2.loadClass("com.lw.demo.classloading.Test");
		Object obj2 = clazz.newInstance();
		
		System.out.println("����ʹ�ò�ͬ�������������class�Ƿ���ͬ --- class:"+  clazz.hashCode() + ";class2:" + clazz2.hashCode());
		
		Method helloMethod = clazz.getDeclaredMethod("hello", null);
		helloMethod.invoke(obj, null);
		System.out.println("obj���������Ϊ:" + obj.getClass().getClassLoader());
		System.out.println("======================================");
		System.out.println("�Զ�����������ĸ�������:" + classLoader1.getParent());
		System.out.println("ϵͳĬ�ϵ�AppClassLoader:" + ClassLoader.getSystemClassLoader());
		System.out.println("AppClassLoader�ĸ��������:"+ClassLoader.getSystemClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@232204a1
		System.out.println("ExtClassLoader�ĸ��������:" + ClassLoader.getSystemClassLoader().getParent().getParent());// null 
	}
}
