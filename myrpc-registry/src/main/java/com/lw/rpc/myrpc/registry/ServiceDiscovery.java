package com.lw.rpc.myrpc.registry;

/**
 * @author liwen
 * @date:2017��11��27�� ����2:10:17
 * @Function: ������
 * @version 1.0
 */
public interface ServiceDiscovery {

	String discover(String serviceName);
}