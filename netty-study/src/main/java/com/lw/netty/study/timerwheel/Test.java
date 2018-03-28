package com.lw.netty.study.timerwheel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;

/**
 * @author liwen
 * @date:2018年3月5日 下午3:35:04
 * @Function: Netty的HashedWheelTimer源码
 * @version 1.0
 */
public class Test {

	public static void main(String[] args) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);
		System.out.println("start:" + LocalDateTime.now().format(formatter));
		hashedWheelTimer.newTimeout(timeout -> {
			System.out.println("timeout:"+ timeout);
			Thread.sleep(3000);
			System.out.println("task1:" + LocalDateTime.now().format(formatter));
		}, 3, TimeUnit.SECONDS);
		// 会主动调用start()方法
		hashedWheelTimer.newTimeout(timeout -> System.out.println("task2:" + LocalDateTime.now().format(formatter)), 4,
				TimeUnit.SECONDS);
		Thread.sleep(10000);
	}
}
