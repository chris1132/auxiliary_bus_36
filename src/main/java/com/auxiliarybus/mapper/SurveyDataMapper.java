package com.auxiliarybus.mapper;

import com.auxiliarybus.entity.SurveyData;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangch on 2018/3/27.
 */
@Repository
public interface SurveyDataMapper {

    SurveyData getSurveyDataById(int id);

    List<SurveyData> getList();

}
