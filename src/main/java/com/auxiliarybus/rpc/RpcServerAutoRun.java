package com.auxiliarybus.rpc;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import rpcPackage.rpc.netty_zookeeper_spring.registry.ServiceRegistry;
import rpcPackage.rpc.netty_zookeeper_spring.server.RpcServer;
import rpcPackage.rpc.netty_zookeeper_spring.server.RpcService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangchaohui on 2018/3/16.
 */
@Component
public class RpcServerAutoRun implements ApplicationRunner, ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(RpcServerAutoRun.class);

    private static Map<String, Object> handlerMap = new HashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String serverAddress = "127.0.0.1:18866";
        ServiceRegistry serviceRegistry = new ServiceRegistry("127.0.0.1:2181");
        RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);

        rpcServer.handlerMap = handlerMap;

//        HelloService helloService = new HelloServiceImpl();
//        rpcServer.addService("HelloService", helloService);

        try {
            rpcServer.start();
        } catch (Exception ex) {
            logger.error("Exception: {}", ex);
        }
    }

    /**
     * ������������ʱ��ɨ��õ����еķ���ӿڼ���ʵ�֣�
     */
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                String[] interfaceatr = interfaceName.split("\\.");
                handlerMap.put(interfaceatr[interfaceatr.length - 1], serviceBean);
            }
        }
    }
}