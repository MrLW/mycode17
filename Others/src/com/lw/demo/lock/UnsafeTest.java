package com.lw.demo.lock;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeTest {

	public static void main(String[] args) throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe"); // Internal reference
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		Player player = (Player) unsafe.allocateInstance(Player.class);
		System.out.println(player);// Player [age=0]
		player.setAge(10);
		System.out.println(player);// Player [age=10]

		// 设置值
		long ageFieldOffset = unsafe.objectFieldOffset(player.getClass().getDeclaredField("age"));
		System.out.println("ageFieldOffset:" + ageFieldOffset);
		if (unsafe.compareAndSwapInt(player, ageFieldOffset, 10, 15)) {
			System.out.println("修改成功:" + player);
		} else {
			System.out.println("修改失败:" + player);
		}
		
	}

}

class Player {
	private int age = 12;

	private Player() {
		this.age = 50;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Player [age=" + age + "]";
	}

}
