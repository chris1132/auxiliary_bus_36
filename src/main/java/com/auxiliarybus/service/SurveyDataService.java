package com.auxiliarybus.service;


import com.auxiliarybus.entity.SurveyData;

import java.util.List;

/**
 * Created by wangch on 2019/3/27
 */

public interface SurveyDataService {

    SurveyData getSurveyDataById(int id);


    List<SurveyData> getList();

    public void insertlocation();
}
