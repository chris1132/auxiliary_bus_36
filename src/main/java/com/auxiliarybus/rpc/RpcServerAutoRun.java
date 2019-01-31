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
import rpcPackage.rpc.netty_zookeeper_spring.server.RpcServer;
import rpcPackage.rpc.netty_zookeeper_spring.server.RpcService;
import rpcPackage.rpc.netty_zookeeper_spring.server.ServiceRegistry;

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
        logger.info("ApplicationArguments run+++++++");
        String serverAddress = "127.0.0.1:18866";
        ServiceRegistry serviceRegistry = new ServiceRegistry("127.0.0.1:2181");
        RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);
        rpcServer.handlerMap = handlerMap;
        rpcServer.afterPropertiesSet();
    }

    /**
     * 服务在启动的时候扫描得到所有的服务接口及其实现：
     * handlerMap key:接口名， value具体实现的方法
     */
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        logger.info("setApplicationContext run+++++++");
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
