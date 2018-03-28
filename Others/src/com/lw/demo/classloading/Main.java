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
	 * 如果Test文件在本项目,则使用的是系统加载器:(sun.misc.Launcher$AppClassLoader@2a139a55)
	 * 如果本地项目没有Test文件,则使用自定义类加载器(com.lw.demo.classloading.Main$MyClassLoader@42a57993)
	 */
	public static void main(String args[]) throws Exception {
		MyClassLoader classLoader1 = new MyClassLoader("D:\\");
		Class clazz = classLoader1.loadClass("com.lw.demo.classloading.Test");
		Object obj = clazz.newInstance();
		
		
		MyClassLoader classLoader2 = new MyClassLoader("D:\\");
		Class clazz2 = classLoader2.loadClass("com.lw.demo.classloading.Test");
		Object obj2 = clazz.newInstance();
		
		System.out.println("测试使用不同类加载器的两个class是否相同 --- class:"+  clazz.hashCode() + ";class2:" + clazz2.hashCode());
		
		Method helloMethod = clazz.getDeclaredMethod("hello", null);
		helloMethod.invoke(obj, null);
		System.out.println("obj的类加载器为:" + obj.getClass().getClassLoader());
		System.out.println("======================================");
		System.out.println("自定义类加载器的父加载器:" + classLoader1.getParent());
		System.out.println("系统默认的AppClassLoader:" + ClassLoader.getSystemClassLoader());
		System.out.println("AppClassLoader的父类加载器:"+ClassLoader.getSystemClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@232204a1
		System.out.println("ExtClassLoader的父类加载器:" + ClassLoader.getSystemClassLoader().getParent().getParent());// null 
	}
}
