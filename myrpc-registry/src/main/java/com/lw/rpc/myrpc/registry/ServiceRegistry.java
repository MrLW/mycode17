package com.lw.rpc.myrpc.registry;

/**
 * @author liwen
 * @date:2017��11��27�� ����2:10:03
 * @Function: ����ע��
 * @version 1.0
 */
public interface ServiceRegistry {

    void register(String serviceName, String serviceAddress);
}