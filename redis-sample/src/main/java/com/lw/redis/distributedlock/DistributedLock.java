package com.lw.redis.distributedlock;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * @author liwen
 * @date:2018年3月7日 下午4:59:29
 * @Function: 分布式锁
 * @version 1.0
 */
public class DistributedLock {
	private final JedisPool jedisPool;

	public DistributedLock(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * 加锁
	 * 
	 * @param locaName
	 *            锁的key
	 * @param acquireTimeout
	 *            获取超时时间
	 * @param timeout
	 *            锁的超时时间
	 * @return 锁标识
	 */
	public String lockWithTimeout(String locaName, long acquireTimeout, long timeout) {
		Jedis conn = jedisPool.getResource();
		// 随机生成一个value
		String identifier = UUID.randomUUID().toString();
		String retIdentifier = null;
		// 锁名，即key值
		String lockKey = "lock:" + locaName;
		// 超时时间，上锁后超过此时间则自动释放锁
		int lockExpire = (int) (timeout / 1000);

		// 获取锁的超时时间，超过这个时间则放弃获取锁
		long end = System.currentTimeMillis() + acquireTimeout;
		while (System.currentTimeMillis() < end) {
			if (conn.setnx(lockKey, identifier) == 1) {
				conn.expire(lockKey, lockExpire);
				// 返回value,用于释放锁时间确认
				retIdentifier = identifier;
				return retIdentifier;
			}
			// 如果没有设置超时时间
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
	 * 释放锁
	 * 
	 * @param lockName
	 *            锁的key
	 * @param identifier
	 *            释放锁的标识
	 * @return
	 */
	public boolean releaseLock(String lockName, String identifier) {
		Jedis conn = null;
		String lockKey = "lock:" + lockName;
		boolean retFlag = false;
		try {
			conn = jedisPool.getResource();
			while (true) {
				// 监视lock,准备开启事务
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
	 * 尝试获取分布式锁
	 * 
	 * @param jedis
	 *            Redis客户端
	 * @param lockKey
	 *            锁
	 * @param requestId
	 *            请求标识
	 * @param expireTime
	 *            超期时间
	 * @return 是否获取成功
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
	     * 释放分布式锁
	     * @param jedis Redis客户端
	     * @param lockKey 锁
	     * @param requestId 请求标识
	     * @return 是否释放成功
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
