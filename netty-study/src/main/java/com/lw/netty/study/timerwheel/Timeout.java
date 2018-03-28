package com.lw.netty.study.timerwheel;

import java.util.Timer;
import java.util.TimerTask;

public interface Timeout {

	Timer timer();

	TimerTask task();

	boolean isExpired();

	boolean isCancelled();

	boolean cancel();
}
