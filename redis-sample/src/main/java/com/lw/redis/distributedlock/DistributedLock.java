package com.lw.redis.distributedlock;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * @author liwen
 * @date:2018��3��7�� ����4:59:29
 * @Function: �ֲ�ʽ��
 * @version 1.0
 */
public class DistributedLock {
	private final JedisPool jedisPool;

	public DistributedLock(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * ����
	 * 
	 * @param locaName
	 *            ����key
	 * @param acquireTimeout
	 *            ��ȡ��ʱʱ��
	 * @param timeout
	 *            ���ĳ�ʱʱ��
	 * @return ����ʶ
	 */
	public String lockWithTimeout(String locaName, long acquireTimeout, long timeout) {
		Jedis conn = jedisPool.getResource();
		// �������һ��value
		String identifier = UUID.randomUUID().toString();
		String retIdentifier = null;
		// ��������keyֵ
		String lockKey = "lock:" + locaName;
		// ��ʱʱ�䣬�����󳬹���ʱ�����Զ��ͷ���
		int lockExpire = (int) (timeout / 1000);

		// ��ȡ���ĳ�ʱʱ�䣬�������ʱ���������ȡ��
		long end = System.currentTimeMillis() + acquireTimeout;
		while (System.currentTimeMillis() < end) {
			if (conn.setnx(lockKey, identifier) == 1) {
				conn.expire(lockKey, lockExpire);
				// ����value,�����ͷ���ʱ��ȷ��
				retIdentifier = identifier;
				return retIdentifier;
			}
			// ���û�����ó�ʱʱ��
			if (conn.setnx(lockKey, identifier) == -1) {
				conn.expire(lockKey, lockExpire);
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		return retIdentifier;
	}

	/**
	 * �ͷ���
	 * 
	 * @param lockName
	 *            ����key
	 * @param identifier
	 *            �ͷ����ı�ʶ
	 * @return
	 */
	public boolean releaseLock(String lockName, String identifier) {
		Jedis conn = null;
		String lockKey = "lock:" + lockName;
		boolean retFlag = false;
		try {
			conn = jedisPool.getResource();
			while (true) {
				// ����lock,׼����������
				conn.watch(lockKey);
				if (identifier.equals(conn.get(lockKey))) {
					Transaction transaction = conn.multi();
					transaction.del(lockKey);
					List<Object> results = transaction.exec();
					if (results == null) {
						continue;
					}
					retFlag = true;
					conn.unwatch();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return retFlag;
	}
	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME = "PX";

	/**
	 * ���Ի�ȡ�ֲ�ʽ��
	 * 
	 * @param jedis
	 *            Redis�ͻ���
	 * @param lockKey
	 *            ��
	 * @param requestId
	 *            �����ʶ
	 * @param expireTime
	 *            ����ʱ��
	 * @return �Ƿ��ȡ�ɹ�
	 */
	public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

		String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

		if (LOCK_SUCCESS.equals(result)) {
			return true;
		}
		return false;

	}
	 private static final Long RELEASE_SUCCESS = 1L;

	    /**
	     * �ͷŷֲ�ʽ��
	     * @param jedis Redis�ͻ���
	     * @param lockKey ��
	     * @param requestId �����ʶ
	     * @return �Ƿ��ͷųɹ�
	     */
	    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

	        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
	        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

	        if (RELEASE_SUCCESS.equals(result)) {
	            return true;
	        }
	        return false;

	    }

}
