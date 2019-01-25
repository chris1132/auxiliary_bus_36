package com.auxiliarybus.aop.aspect;

import com.auxiliarybus.util.StringUtil;
import com.auxiliarybus.util.URLUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangchaohui on 2019/1/3
 */
@Aspect
@Component
public class ControllerXssAspect extends BaseAspect {


    //参数检查是否是脚本 xss
    @Before("com.auxiliarybus.aop.pointcut.Pointcut.paramCheckPointCut()")
    public void beforeCheck(JoinPoint joinPoint)throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        Object[] args = joinPoint.getArgs();
        for(Object obj:args) {
            if(StringUtil.isScript(obj.toString())){
                try {
                    response.sendRedirect(request.getContextPath()+"/parameterIllegal");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        }

        ModelAndView mv = getModelAndView(args);
        mv.addObject("staticFileDomain", URLUtil.staticFileDomain);
    }

}
