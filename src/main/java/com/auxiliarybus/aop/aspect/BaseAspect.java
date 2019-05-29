package com.auxiliarybus.aop.aspect;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangchaohui on 2019/1/4
 */
public class BaseAspect {


    public ModelAndView getModelAndView(Object[] args) {
        for (Object obj : args) {
            if (obj instanceof ModelAndView) {
                return (ModelAndView) obj;
            }
        }
        return null;
    }

    public HttpServletRequest getHttpServletRequest(Object[] args) {
        for (Object obj : args) {
            if (obj instanceof HttpServletRequest) {
                return (HttpServletRequest) obj;
            }
        }
        return null;
    }
}
