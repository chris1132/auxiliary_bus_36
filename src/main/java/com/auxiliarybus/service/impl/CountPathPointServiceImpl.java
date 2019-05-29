package com.auxiliarybus.service.impl;

import com.auxiliarybus.entity.BusSiteLocation;
import com.auxiliarybus.entity.Questionnaire;
import com.auxiliarybus.mapper.BusSiteLocationMapper;
import com.auxiliarybus.mapper.QuestionnaireMapper;
import com.auxiliarybus.mapper.SurveyDataMapper;
import com.auxiliarybus.util.entity.SiteCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.auxiliarybus.prim.plugin.util.DistanceComputerUtil.getDistance;

/**
 * Created by wangch on 2019/3/29
 */
@Service
public class CountPathPointServiceImpl {

    @Autowired
    BusSiteLocationMapper busSiteLocationMapper;

    @Autowired
    SurveyDataMapper surveyDataMapper;

    @Autowired
    QuestionnaireMapper questionnaireMapper;


    public ExecutorService executors = Executors.newSingleThreadExecutor();

    public static final int DISTANCE_LIMIT = 300;


    /**
     * 统计各站点能拟合的需求点
     * 思路：从一个站点辐射300m  所有和站点欧式距离在300以内的需求点拟合到该站点。如果某一个需求点已经拟合到一个站点上，则比较距离，最终将需求点拟合到最近的站点
     */
    public void computingAggregationPoints() {

        var busSiteLocationList = busSiteLocationMapper.getList();

        var questionnaireList = questionnaireMapper.getList();

        /**
         * key:站点site_code   value:该站点聚合需求数
         * 收集各个站点聚合的需求点数
         * */
        HashMap<Long, Integer> siteCountMap = new HashMap<Long, Integer>();


        /**
         * 站点信息
         * */
        HashMap<Long, BusSiteLocation> siteinfoMap = new HashMap<Long, BusSiteLocation>();

        /**
         * key 需求点坐标,value 需求点对应的聚合站点信息
         * 记录各需求点归属的站点
         * */
        HashMap<Integer, SiteCount> surveyPointCountMap = new HashMap<Integer, SiteCount>();

        for (BusSiteLocation busSiteLocation : busSiteLocationList) {
            Double pointlng = Double.valueOf(busSiteLocation.getLongitude());
            Double pointlat = Double.valueOf(busSiteLocation.getLatitude());

            siteinfoMap.put(busSiteLocation.getSiteCode(), busSiteLocation);
            for (Questionnaire questionnaireData : questionnaireList) {
                int questionnaireId = questionnaireData.getId();
                Double surveypointlng = Double.valueOf(questionnaireData.getLongitude());
                Double surveypointlat = Double.valueOf(questionnaireData.getLatitude());

                double dis = getDistance(pointlng, pointlat, surveypointlng, surveypointlat);
                if (dis < DISTANCE_LIMIT) {
                    long siteCode = busSiteLocation.getSiteCode();
                    //如果该需求点已和某个站点关联
                    if (surveyPointCountMap.containsKey(questionnaireId)) {

                        SiteCount siteCountold = surveyPointCountMap.get(questionnaireId);

                        //如果某一个需求点已经拟合到一个站点上，则比较距离，最终将需求点拟合到最近的站点
                        if (dis < siteCountold.getDis()) {
                            //如果当前计算的距离小于之前的距离，替换
                            surveyPointCountMap.replace(questionnaireId, siteCountold, new SiteCount(siteCode, dis));

                            //原来站点的拟合需求点数减一
                            long oldSitecode = siteCountold.getSite_code();
                            Integer countold = siteCountMap.get(oldSitecode);
                            siteCountMap.replace(oldSitecode, countold, --countold);


                            siteCountMapflush(siteCountMap, siteCode);
                        }
                    } else {
                        surveyPointCountMap.put(questionnaireId, new SiteCount(siteCode, dis));

                        siteCountMapflush(siteCountMap, siteCode);

                    }
                }
            }
        }


        executors.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start____update site code");
                for (Map.Entry entry : surveyPointCountMap.entrySet()) {
                    int questionnaireId = Integer.valueOf(entry.getKey().toString());
                    SiteCount siteCount = (SiteCount) entry.getValue();
                    Questionnaire questionnaire = new Questionnaire(questionnaireId, siteCount.getSite_code());
                    questionnaireMapper.updateaffiliatedSiteCode(questionnaire);
                }
                System.out.println("finish");
            }
        });


//        List<BusSiteLocation> list = new ArrayList<>();
//        for(Map.Entry entry:siteCountMap.entrySet()){
//            if(Integer.valueOf(entry.getValue().toString())>0){
//                int surveycount = Integer.valueOf(entry.getValue().toString());
//                System.out.println("结果："+entry.getKey()+"_______"+entry.getValue());
//                BusSiteLocation busSiteLocation = siteinfoMap.get(entry.getKey());
//                busSiteLocation.setSurveyCount(surveycount);
//                list.add(busSiteLocation);
//            }
//        }
//        exportHeroInfo(list,"各站点周围"+DISTANCE_LIMIT+"米内需求点聚合数_高德.xlsx");

    }

    public void siteCountMapflush(HashMap<Long, Integer> siteCountMap, long siteCode) {
        if (siteCountMap.containsKey(siteCode)) {
            Integer countnew = siteCountMap.get(siteCode);
            siteCountMap.put(siteCode, ++countnew);
        } else {
            siteCountMap.put(siteCode, 1);
        }
    }


}
