package com.auxiliarybus.service.impl;


import com.auxiliarybus.entity.PointLocation;
import com.auxiliarybus.entity.Questionnaire;
import com.auxiliarybus.entity.SurveyData;
import com.auxiliarybus.mapper.QuestionnaireMapper;
import com.auxiliarybus.mapper.SurveyDataMapper;
import com.auxiliarybus.service.SurveyDataFunctionInterface;
import com.auxiliarybus.service.SurveyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangch on 2019/3/27
 */
@Service
public class SurveyDataServiceImpl implements SurveyDataService {

    @Autowired(required = false)
    private SurveyDataMapper surveyDataMapper;

    @Autowired(required = false)
    private QuestionnaireMapper questionnaireMapper;


    public SurveyData getSurveyDataById(int id) {
        return surveyDataMapper.getSurveyDataById(id);
    }


    public List<SurveyData> getList() {
        return surveyDataMapper.getList();
    }

    public void insertlocation() {

        SurveyDataFunctionInterface<List<SurveyData>, List<Questionnaire>> insertlocation = (List<SurveyData> list) -> {
            List<Questionnaire> questionnaireList = new ArrayList<>();
            for (SurveyData data : list) {
                String[] localarray = data.getLocation().split(",");
                Questionnaire questionnaire = new Questionnaire(data.getId(), data.getHomeLocation(), localarray[0], localarray[1], 0, data.getGrade(),data.getNightBus());
                questionnaireList.add(questionnaire);
            }
            return questionnaireList;

        };
        List<Questionnaire> list = insertlocation.getlocationlist(this.getList());
        questionnaireMapper.insertQuestionBatch(list);

    }

}
