package com.auxiliarybus.controller.WarnController;

import com.auxiliarybus.entity.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangchaohui on 2019/1/4
 */
@RestController
public class ParameterIllegalController {

    //参数校验
    @RequestMapping(value = "/parameterIllegal", method = RequestMethod.GET)
    public ModelAndView parameterIllegal(ModelAndView modelAndView) {
        System.out.println("illegal paramter");
        modelAndView.addObject("name","参数错误");
        modelAndView.setViewName("warn");
        System.out.println("illegal paramter");
        return modelAndView;
    }
}
