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
        String serverAddress = "192.168.2.144:18866";
        ServiceRegistry serviceRegistry = new ServiceRegistry("192.168.2.144:2181");
        RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);

        rpcServer.handlerMap = handlerMap;

        try {
            rpcServer.start();
        } catch (Exception ex) {
            logger.error("Exception: {}", ex);
        }
    }

    /**
     * ������������ʱ��ɨ��õ����еķ���ӿڼ���ʵ�֣�
     * handlerMap key:�ӿ����� value����ʵ�ֵķ���
     */
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                //com.auxiliarybus.service.impl.StudentServiceImpl
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }
}
