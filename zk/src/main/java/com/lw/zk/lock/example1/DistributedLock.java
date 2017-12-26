package com.lw.zk.lock.example1;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {
	
	/** ��ȡ�������û�еõ��͵ȴ� */
	public void acquire() throws Exception;

	/**
	 * ��ȡ����ֱ����ʱ
	 * 
	 * @param time��ʱʱ��
	 * @param unit
	 *            time�����ĵ�λ
	 * @return�Ƿ��ȡ����
	 * @throws Exception
	 */
	public boolean acquire(long time, TimeUnit unit) throws Exception;

	/**
	 * �ͷ���
	 * 
	 * @throws Exception
	 */
	public void release() throws Exception;
}
