package com.lw.zk.lock.example1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

public class BaseDistributedLock {

	private final ZkClient client;
	private final String path;
	private final String basePath;
	private final String lockName;
	private static final Integer MAX_RETRY_COUNT = 10;

	public BaseDistributedLock(ZkClient client, String path, String lockName) {
		this.client = client;
		this.basePath = path;
		this.path = path.concat("/").concat(lockName);
		this.lockName = lockName;
	}

	private void deleteOurPath(String ourPath) throws Exception {
		client.delete(ourPath);
	}

	private String createLockNode(ZkClient client, String path) throws Exception {
		return client.createEphemeralSequential(path, null);
	}

	/**
	 * ��ȡ���ĺ��ķ���
	 * 
	 * @param startMillis
	 * @param millisToWait
	 * @param ourPath
	 * @return
	 * @throws Exception
	 */
	private boolean waitToLock(long startMillis, Long millisToWait, String ourPath) throws Exception {

		boolean haveTheLock = false;
		boolean doDelete = false;

		try {
			while (!haveTheLock) {
				// �÷���ʵ�ֻ�ȡlocker�ڵ��µ�����˳��ڵ㣬���Ҵ�С��������
				List<String> children = getSortedChildren();
				String sequenceNodeName = ourPath.substring(basePath.length() + 1);

				// ����ղſͻ��˴�����˳��ڵ���locker�������ӽڵ�������λ�ã����������Ϊ0�����ʾ��ȡ������
				int ourIndex = children.indexOf(sequenceNodeName);

				/*
				 * �����getSortedChildren��û���ҵ�֮ǰ������[��ʱ]˳��ڵ㣬���ʾ���������������϶�����
				 * Zookeeper��Ϊ���ӶϿ���ɾ�������Ǵ����Ľڵ㣬��ʱ��Ҫ�׳��쳣������һ��ȥ���� ��һ���������ǲ�����쳣������ִ������ָ���Ĵ��� �������
				 * attemptLock����
				 */
				if (ourIndex < 0) {
					throw new ZkNoNodeException("�ڵ�û���ҵ�: " + sequenceNodeName);
				}

				// �����ǰ�ͻ��˴����Ľڵ���locker�ӽڵ��б���λ�ô���0����ʾ�����ͻ����Ѿ���ȡ����
				// ��ʱ��ǰ�ͻ�����Ҫ�ȴ������ͻ����ͷ�����
				boolean isGetTheLock = ourIndex == 0;

				// ����ж������ͻ����Ƿ��Ѿ��ͷ����������ӽڵ��б��л�ȡ�����Լ���С���ĸ��ڵ㣬�����佨������
				String pathToWatch = isGetTheLock ? null : children.get(ourIndex - 1);

				if (isGetTheLock) {
					haveTheLock = true;
				} else {
					// �����С�Ľڵ㱻ɾ���ˣ����ʾ��ǰ�ͻ��˵Ľڵ�Ӧ������С���ˣ�����ʹ��CountDownLatch��ʵ�ֵȴ�
					String previousSequencePath = basePath.concat("/").concat(pathToWatch);
					final CountDownLatch latch = new CountDownLatch(1);
					final IZkDataListener previousListener = new IZkDataListener() {

						// ��С�ڵ�ɾ���¼�����ʱ����countDownLatch�����ȴ�
						// ��ʱ����Ҫ�����ó���ص�while�������ж�һ�Σ�
						public void handleDataDeleted(String dataPath) throws Exception {
							latch.countDown();
						}

						public void handleDataChange(String dataPath, Object data) throws Exception {
							// ignore
						}
					};

					try {
						// ����ڵ㲻���ڻ�����쳣
						client.subscribeDataChanges(previousSequencePath, previousListener);

						if (millisToWait != null) {
							millisToWait -= (System.currentTimeMillis() - startMillis);
							startMillis = System.currentTimeMillis();
							if (millisToWait <= 0) {
								doDelete = true; // timed out - delete our node
								break;
							}

							latch.await(millisToWait, TimeUnit.MICROSECONDS);
						} else {
							latch.await();
						}

					} catch (ZkNoNodeException e) {
						// ignore
					} finally {
						client.unsubscribeDataChanges(previousSequencePath, previousListener);
					}
				}
			}
		} catch (Exception e) {
			// �����쳣��Ҫɾ���ڵ�
			doDelete = true;
			throw e;

		} finally {
			// �����Ҫɾ���ڵ�
			if (doDelete) {
				deleteOurPath(ourPath);
			}
		}
		return haveTheLock;
	}

	private String getLockNodeNumber(String str, String lockName) {
		int index = str.lastIndexOf(lockName);
		if (index >= 0) {
			index += lockName.length();
			return index <= str.length() ? str.substring(index) : "";
		}
		return str;
	}

	private List<String> getSortedChildren() throws Exception {
		try {
			List<String> children = client.getChildren(basePath);
			Collections.sort(children, new Comparator<String>() {
				public int compare(String lhs, String rhs) {
					return getLockNodeNumber(lhs, lockName).compareTo(getLockNodeNumber(rhs, lockName));
				}
			});
			return children;

		} catch (ZkNoNodeException e) {
			client.createPersistent(basePath, true);
			return getSortedChildren();
		}
	}

	protected void releaseLock(String lockPath) throws Exception {
		deleteOurPath(lockPath);
	}

	protected String attemptLock(long time, TimeUnit unit) throws Exception {
		final long startMillis = System.currentTimeMillis();
		final Long millisToWait = (unit != null) ? unit.toMillis(time) : null;

		String ourPath = null;
		boolean hasTheLock = false;
		boolean isDone = false;
		int retryCount = 0;

		// ����������Ҫ����һ��
		while (!isDone) {
			isDone = true;

			try {
				// createLockNode������locker��basePath�־ýڵ㣩�´����ͻ���Ҫ��ȡ����[��ʱ]˳��ڵ�
				ourPath = createLockNode(client, path);
				/**
				 * �÷��������ж��Լ��Ƿ��ȡ�����������Լ�������˳��ڵ���locker�������ӽڵ����Ƿ���С
				 * ���û�л�ȡ��������ȴ������ͻ��������ͷţ������Ժ�����ֱ����ȡ�������߳�ʱ
				 */
				hasTheLock = waitToLock(startMillis, millisToWait, ourPath);

			} catch (ZkNoNodeException e) {
				if (retryCount++ < MAX_RETRY_COUNT) {
					isDone = false;
				} else {
					throw e;
				}
			}
		}

		if (hasTheLock) {
			return ourPath;
		}

		return null;
	}
}
