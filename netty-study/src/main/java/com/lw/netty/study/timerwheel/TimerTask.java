package com.lw.netty.study.timerwheel;

public interface TimerTask {
	void run(Timeout timeout, String argv) throws Exception;
}
