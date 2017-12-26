package com.lw.rpc.myrpc.registry;

/**
 * @author liwen
 * @date:2017年11月27日 下午2:10:17
 * @Function: 服务发现
 * @version 1.0
 */
public interface ServiceDiscovery {

	String discover(String serviceName);
}