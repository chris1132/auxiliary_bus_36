package com.auxiliarybus.service.impl;

import com.auxiliarybus.entity.BusSiteLocation;
import com.auxiliarybus.mapper.BusSiteLocationMapper;
import com.auxiliarybus.service.BusSiteLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangch on 2019/3/29
 */
@Service
public class BusSiteLocationServiceimpl implements BusSiteLocationService {

    @Autowired
    BusSiteLocationMapper busSiteLocationMapper;


    public int insertBusSiteBatch(List<BusSiteLocation> busSiteLocationList) {
        return busSiteLocationMapper.insertBusSiteBatch(busSiteLocationList);
    }

    public List<BusSiteLocation> getList() {
        return busSiteLocationMapper.getList();
    }


    public List<BusSiteLocation> getListwithcount(int gradeType) {

        var list = busSiteLocationMapper.getListwithcount(gradeType);

        HashMap<String, BusSiteLocation> siteMap = new HashMap<>();

        for (int i = 0; i < (list.size() - 1); i++) {
            var busSiteLocation = list.get(i);
            BusSiteLocation presiteLocation = siteMap.get(busSiteLocation.getSiteName());
            //相同站点名，合并，以拟合人数多为主
            if (null != presiteLocation) {
                int oldSurveyCount = presiteLocation.getSurveyCount();
                int currentSurveyCount = busSiteLocation.getSurveyCount();
                int sumSurveyCount = oldSurveyCount + currentSurveyCount;
                if (oldSurveyCount < currentSurveyCount) {
                    busSiteLocation.setSurveyCount(sumSurveyCount);
                    siteMap.put(presiteLocation.getSiteName(), busSiteLocation);
                } else {
                    presiteLocation.setSurveyCount(sumSurveyCount);
                    siteMap.put(presiteLocation.getSiteName(), presiteLocation);
                }
            } else {
                siteMap.put(busSiteLocation.getSiteName(), busSiteLocation);
            }

        }

        List<BusSiteLocation> newlist = new ArrayList<>();
        for (Map.Entry entry : siteMap.entrySet()) {
            newlist.add((BusSiteLocation) entry.getValue());
        }
        return newlist;
    }

}
