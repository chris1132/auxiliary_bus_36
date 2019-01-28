package com.auxiliarybus.aop.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by wangchaohui on 2019/1/17
 */

@Aspect
@Component
public class Pointcut {

    @org.aspectj.lang.annotation.Pointcut("execution(* com.auxiliarybus.controller..*(..)) && !execution(* com.auxiliarybus.controller.admin.CreateLocationController.*(..))")
    public void paramCheckPointCut(){}


}
