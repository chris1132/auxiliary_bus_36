package com.auxiliarybus.mapper;

import com.auxiliarybus.entity.BusSiteLocation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangchaohui on 2019/1/25
 */
@Repository
public interface BusSiteLocationMapper {

    public List<BusSiteLocation> getList();


}
