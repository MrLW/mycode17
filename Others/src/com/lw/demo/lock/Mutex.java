package com.lw.demo.lock;

import java.io.Serializable;
import java.nio.channels.IllegalBlockingModeException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock,Serializable{
	
	
	// �Զ���ͬ����
	private static class Sync extends AbstractQueuedSynchronizer{
		// �ж��Ƿ�������״̬
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1 ;
		}
		// ���Ի�ȡ��Դ,��������
		@Override
		protected boolean tryAcquire(int arg) {
			assert arg == 1; 
			if(compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true ;
			}
			return false;
		}
		// �����ͷ���Դ,��������
		@Override
		protected boolean tryReleaseShared(int arg) {
			assert arg == 1 ;
			if(getState() == 0 ) { 
				throw new IllegalBlockingModeException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true ;
		}
	}
	
	// ����ʵ��ͬ����
	private Sync sync = new Sync() ;

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
