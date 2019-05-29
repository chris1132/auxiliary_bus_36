package com.auxiliarybus.controller.admin;

import com.auxiliarybus.entity.BusSiteLocation;
import com.auxiliarybus.entity.SurveyData;
import com.auxiliarybus.service.BusSiteLocationService;
import com.auxiliarybus.service.QuestionnaireService;
import com.auxiliarybus.service.SurveyDataService;
import com.auxiliarybus.service.impl.CountPathPointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

import static com.auxiliarybus.util.CSVUtils.exportCsv;
import static com.auxiliarybus.util.PoiImport.importLocationExcel;

/**
 * Created by wangch on 2019/3/26
 */

@RestController
public class ImportExcelController {
    @Autowired
    public QuestionnaireService questionnaireService;

    @Autowired
    public BusSiteLocationService busSiteLocationService;

    @Autowired
    private SurveyDataService surveyDataService;

    @Autowired
    public CountPathPointServiceImpl countPathPointService;

    @RequestMapping(value = "/getSurvey.html", method = RequestMethod.GET)
    public SurveyData getSurvey() {
        return surveyDataService.getSurveyDataById(4);
    }

    @RequestMapping(value = "/transport4willing.html", method = RequestMethod.GET)
    public ModelAndView transport4willing(ModelAndView model) {

        model.setViewName("show/transport4willing");
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String rpc(@RequestParam(value = "id", defaultValue = "1", required = false) int id) throws Exception {
//        List<Questionnaire> questionnaireList = importExcel();
//        questionnaireService.insertQuestionBatch(questionnaireList);

        List<BusSiteLocation> busSiteLocationList = importLocationExcel();
        busSiteLocationService.insertBusSiteBatch(busSiteLocationList);
        return "success";
    }


    @ResponseBody
    @RequestMapping(value = "/insertlocation", method = RequestMethod.GET)
    public String insertlocation() {
        surveyDataService.insertlocation();
        return "success";
    }


    @ResponseBody
    @RequestMapping(value = "/computerpath", method = RequestMethod.GET)
    public String computerpath() throws Exception {
        countPathPointService.computingAggregationPoints();

        return "success";
    }


    @ResponseBody
    @RequestMapping(value = "/export2csv", method = RequestMethod.GET)
    public String export2csv() {
        List<BusSiteLocation> busSiteLocationList = busSiteLocationService.getListwithcount(1);
        File file = new File("F:\\excel\\BusSite_School.csv");
        exportCsv(file, busSiteLocationList);
        return "success";
    }

/*    @ResponseBody
    @RequestMapping(value = "/prim", method = RequestMethod.GET)
    public String prim() {

        */

    /**
     * gradeType
     * 1 7年级和8年级
     * 2 高一高二
     * 3 高一
     *//*
        int gradeType = 2;
        var list = busSiteLocationService.getListwithcount(gradeType);
        addRoot(list);

        //顶点数
        int verNum = list.size();
        //边数
        int edgeNum = verNum*(verNum-1);

        GraphWG graph=new GraphWG(edgeNum,verNum);
        CreateWG createWG=new CreateWG();

        createWG.initialWg(graph,list);
//        createWG.outputWG(graph);
        Prim prim=new Prim(verNum);
        prim.primSpanningTree(graph);

        return "success";
    }*/
    public void addRoot(List<BusSiteLocation> list) {
        BusSiteLocation busSiteLocation = new BusSiteLocation(0, 0, "嘉兴一中实验南门", "120.772816", "30.738425");
        list.add(busSiteLocation);
    }


}
