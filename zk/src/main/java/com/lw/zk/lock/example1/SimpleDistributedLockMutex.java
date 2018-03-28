package com.lw.zk.lock.example1;

import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.ZkClient;

public class SimpleDistributedLockMutex extends BaseDistributedLock  implements  DistributedLock {

	public SimpleDistributedLockMutex(ZkClient client, String path, String lockName) {
		super(client, path, lockName);
		// TODO Auto-generated constructor stub
	}

	public void acquire() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean acquire(long time, TimeUnit unit) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public void release() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
