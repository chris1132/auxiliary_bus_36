package com.auxiliarybus.mapper;

import com.auxiliarybus.entity.Questionnaire;
import com.auxiliarybus.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangch on 2019/3/26
 */

@Repository
public interface QuestionnaireMapper {
    int insertQuestionBatch(List<Questionnaire> questionnaireList);

    List<Questionnaire> getList();

    int updateaffiliatedSiteCode(Questionnaire questionnaire);
}
