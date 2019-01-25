package com.auxiliarybus.aop.aspect;

import com.auxiliarybus.util.URLUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangchaohui on 2019/1/17

 */

@Aspect
@Component
public class UnitJoinAspect extends BaseAspect {

//    @Before("com.auxiliarybus.aop.pointcut.Pointcut.before() && !com.auxiliarybus.aop.pointcut.Pointcut.paramCheckPointCut()")
//    public void utilPackage(JoinPoint joinPoint){
//
//        ModelAndView mv = getModelAndView(joinPoint.getArgs());
//        mv.addObject("staticFileDomain", URLUtil.staticFileDomain);
//
//        System.out.println("aspect:"+mv.getModelMap().get("staticFileDomain"));
//
//    }


}
