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
     * ͳ�Ƹ�վ������ϵ������
     * ˼·����һ��վ�����300m  ���к�վ��ŷʽ������300���ڵ��������ϵ���վ�㡣���ĳһ��������Ѿ���ϵ�һ��վ���ϣ���ȽϾ��룬���ս��������ϵ������վ��
     */
    public void computingAggregationPoints() {

        var busSiteLocationList = busSiteLocationMapper.getList();

        var questionnaireList = questionnaireMapper.getList();

        /**
         * key:վ��site_code   value:��վ��ۺ�������
         * �ռ�����վ��ۺϵ��������
         * */
        HashMap<Long, Integer> siteCountMap = new HashMap<Long, Integer>();


        /**
         * վ����Ϣ
         * */
        HashMap<Long, BusSiteLocation> siteinfoMap = new HashMap<Long, BusSiteLocation>();

        /**
         * key ���������,value ������Ӧ�ľۺ�վ����Ϣ
         * ��¼������������վ��
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
                    //�����������Ѻ�ĳ��վ�����
                    if (surveyPointCountMap.containsKey(questionnaireId)) {

                        SiteCount siteCountold = surveyPointCountMap.get(questionnaireId);

                        //���ĳһ��������Ѿ���ϵ�һ��վ���ϣ���ȽϾ��룬���ս��������ϵ������վ��
                        if (dis < siteCountold.getDis()) {
                            //�����ǰ����ľ���С��֮ǰ�ľ��룬�滻
                            surveyPointCountMap.replace(questionnaireId, siteCountold, new SiteCount(siteCode, dis));

                            //ԭ��վ���������������һ
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
//                System.out.println("�����"+entry.getKey()+"_______"+entry.getValue());
//                BusSiteLocation busSiteLocation = siteinfoMap.get(entry.getKey());
//                busSiteLocation.setSurveyCount(surveycount);
//                list.add(busSiteLocation);
//            }
//        }
//        exportHeroInfo(list,"��վ����Χ"+DISTANCE_LIMIT+"���������ۺ���_�ߵ�.xlsx");

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
