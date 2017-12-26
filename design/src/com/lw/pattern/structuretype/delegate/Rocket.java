package com.lw.pattern.structuretype.delegate;

public class Rocket {

	IRocketDelegate delegate = null;

	public Rocket(IRocketDelegate delegate) {
		this.delegate = delegate;
	}

	private long getRocketStartTime() {
		long startTime = delegate.startAtTime();
		return startTime;
	}

	private long getRocketEndTime() {
		long endTime = delegate.endAtTime();
		return endTime;
	}

	public boolean isOk() {
		// ��ʱ0.1��Ϊʧ��
		if (getRocketEndTime() - getRocketStartTime() >= 100) {
			delegate.sendDidFail();
			return false;
		}
		return true;
	}
}
