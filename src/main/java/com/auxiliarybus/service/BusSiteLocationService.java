package com.auxiliarybus.service;

import com.auxiliarybus.entity.BusSiteLocation;

import java.util.List;

/**
 * Created by wangch on 2019/3/29
 */
public interface BusSiteLocationService {
    int insertBusSiteBatch(List<BusSiteLocation> busSiteLocationList);

    public List<BusSiteLocation> getList();

    public List<BusSiteLocation> getListwithcount(int gradeType);
}
