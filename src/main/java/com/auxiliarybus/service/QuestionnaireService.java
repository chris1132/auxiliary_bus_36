package com.auxiliarybus.service;

import com.auxiliarybus.entity.Questionnaire;

import java.util.List;

/**
 * Created by wangch on 2019/3/26
 */
public interface QuestionnaireService {

    int insertQuestionBatch(List<Questionnaire> questionnaireList);

    List<Questionnaire> getList();
}
