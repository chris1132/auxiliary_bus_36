package com.auxiliarybus.service;

/**
 * Created by wangch on 2019/4/24
 */


@FunctionalInterface
public interface SurveyDataFunctionInterface<F, T> {

    T getlocationlist(F f);


}
