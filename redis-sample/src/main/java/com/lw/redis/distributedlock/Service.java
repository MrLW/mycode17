package com.lw.redis.distributedlock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Service {

	private static JedisPool pool = null;
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// �������������
		config.setMaxTotal(200);
		// ������������
		config.setMaxIdle(8);
		// �������ȴ�ʱ��
		config.setMaxWaitMillis(1000 * 100);
		// ��borrowһ��jedisʵ��ʱ���Ƿ���Ҫ��֤����Ϊtrue��������jedisʵ�����ǿ��õ�
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, "127.0.0.1", 6379, 3000);
	}

	DistributedLock lock = new DistributedLock(pool);

	int n = 500;

	public void seckill() {
		// ��������valueֵ�����ͷ���ʱ������ж�
		String indentifier = lock.lockWithTimeout("resource", 5000, 1000);
		System.out.println(Thread.currentThread().getName() + "�������");
		System.out.println(--n);
		lock.releaseLock("resource", indentifier);
	}
}
