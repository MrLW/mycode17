package com.lw.rpc.myrpc.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class TestZooKeeperWatcher {

	private static ZooKeeper zk;

	public static void main(String[] args) {
		try {
			String address = "192.168.1.83:2181";
			int sessionTimeout = 3000;
			zk = new ZooKeeper(address, sessionTimeout, new Watcher() {

				public void process(WatchedEvent event) {
					if (event.getType() == null || "".equals(event.getType())) {
						return;
					}
					System.out.println("触发：" + event.getType() + "事件");
				}
			});
			System.out.println("zookeeper创建成功");

			// 创建根节点
			zk.create("/registry", "注册中心-数据".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("/testzk创建成功");
			
			// 开始创建子目录节点
			//zk.create("/testzk/node02", "我是node01".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				zk.close();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
