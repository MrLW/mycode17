package com.lw.pattern.delegate;

public interface IRocketDelegate {
	public abstract long startAtTime();

	public abstract long endAtTime();

	public abstract void sendDidFail();
}
