package com.lw.rpc.myrpc.registry;

/**
 * @author liwen
 * @date:2017年11月27日 下午2:10:03
 * @Function: 服务注册
 * @version 1.0
 */
public interface ServiceRegistry {

    void register(String serviceName, String serviceAddress);
}