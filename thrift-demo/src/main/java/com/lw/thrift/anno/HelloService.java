package com.lw.thrift.anno;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

@ThriftService("HelloService")
public interface HelloService {
	
    @ThriftMethod
    public String sayHello(@ThriftField(name = "user") User user);

}
