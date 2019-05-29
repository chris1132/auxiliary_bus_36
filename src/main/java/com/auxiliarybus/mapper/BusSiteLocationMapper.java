package com.auxiliarybus.mapper;

import com.auxiliarybus.entity.BusSiteLocation;
import com.auxiliarybus.entity.Questionnaire;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangchaohui on 2019/1/25
 */
@Repository
public interface BusSiteLocationMapper {

    int insertBusSiteBatch(List<BusSiteLocation> busSiteLocationList);

    public List<BusSiteLocation> getList();

    public List<BusSiteLocation> getListwithcount(@Param(value = "gradeType") int gradeType);


}
