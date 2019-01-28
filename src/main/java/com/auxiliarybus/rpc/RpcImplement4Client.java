package com.auxiliarybus.rpc;


import org.springframework.stereotype.Component;
import rpcPackage.rpc.netty_zookeeper_spring.client.RPCFuture;
import rpcPackage.rpc.netty_zookeeper_spring.client.RpcClient;
import rpcPackage.rpc.netty_zookeeper_spring.client.proxy.IAsyncObjectProxy;
import rpcPackage.rpc.netty_zookeeper_spring.client.proxy.ObjectProxy;
import rpcPackage.rpc.netty_zookeeper_spring.registry.ServiceDiscovery;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangchaohui on 2018/3/19.
 */
@Component
public class RpcImplement4Client {

    private static IAsyncObjectProxy iAsyncObjectProxy = new ObjectProxy<>();

    private long timeOut = 5000L;

    private TimeUnit timeOutUnit = TimeUnit.MILLISECONDS;

    public Object client4Async(String className, String funcName, Object... args) throws Exception {
        final RpcClient rpcClient = new RpcClient(new ServiceDiscovery("127.0.0.1:2181"));
        RPCFuture future = iAsyncObjectProxy.call(className, funcName, args);
        Object result = future.get(timeOut, timeOutUnit);
        return result;
    }


}
