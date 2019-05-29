package com.auxiliarybus.rpc;


import org.springframework.stereotype.Component;
import rpcPackage.rpc.netty_zookeeper_spring.client.RpcClient;
import rpcPackage.rpc.netty_zookeeper_spring.client.ServiceDiscovery;
import rpcPackage.rpc.netty_zookeeper_spring.util.RpcRequest;
import rpcPackage.rpc.netty_zookeeper_spring.util.RpcResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangchaohui on 2018/3/19.
 */
@Component
public class RpcImplement4Client {

    private static final Map<String, Object> typeNameMap = new HashMap<String, Object>();

    static {
        typeNameMap.put("java.lang.Integer", Integer.TYPE);
        typeNameMap.put("java.lang.Long", Long.TYPE);
        typeNameMap.put("java.lang.Float", Float.TYPE);
        typeNameMap.put("java.lang.Double", Double.TYPE);
        typeNameMap.put("java.lang.Character", Character.TYPE);
        typeNameMap.put("java.lang.Boolean", Boolean.TYPE);
        typeNameMap.put("java.lang.Short", Short.TYPE);
        typeNameMap.put("java.lang.Byte", Byte.TYPE);
    }

    private long timeOut = 5000L;

    private TimeUnit timeOutUnit = TimeUnit.MILLISECONDS;

    public Object client4Async(String className, String funcName, Object... args) throws Exception {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("127.0.0.1:2181");
        String serverAddress = serviceDiscovery.discoverService(className);
        String[] addressAry = serverAddress.split(":");
        final RpcClient rpcClient = new RpcClient(addressAry[0], Integer.parseInt(addressAry[1]));
        RpcResponse response = rpcClient.sendRequest(createRequest(className, funcName, args));
        Object result = response.getResult();
        return result;
    }

    private RpcRequest createRequest(String className, String methodName, Object[] args) {
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(className);

        request.setMethodName(methodName);
        request.setParameters(args);

        Class[] parameterTypes = new Class[args.length];
        // 封装参数类型
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = getClassType(args[i]);
        }
        request.setParameterTypes(parameterTypes);
        return request;
    }

    private Class<?> getClassType(Object obj) {
        Class<?> classType = obj.getClass();
        String typeName = classType.getName();
        return (Class<?>) typeNameMap.get(typeName);
    }

}
