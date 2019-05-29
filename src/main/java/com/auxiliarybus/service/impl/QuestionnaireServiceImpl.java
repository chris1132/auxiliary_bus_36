package com.auxiliarybus.service.impl;

import com.auxiliarybus.entity.Questionnaire;
import com.auxiliarybus.mapper.QuestionnaireMapper;
import com.auxiliarybus.mapper.StudentMapper;
import com.auxiliarybus.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangch on 2019/3/26
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired(required = false)
    private QuestionnaireMapper questionnaireMapper;

    public int insertQuestionBatch(List<Questionnaire> questionnaireList) {
        return questionnaireMapper.insertQuestionBatch(questionnaireList);
    }

    public List<Questionnaire> getList() {
        return questionnaireMapper.getList();
    }
}
