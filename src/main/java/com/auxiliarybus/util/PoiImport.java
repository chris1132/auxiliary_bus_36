package com.auxiliarybus.util;

import com.auxiliarybus.entity.BusSiteLocation;
import com.auxiliarybus.entity.Questionnaire;
import com.auxiliarybus.util.entity.Gps;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.auxiliarybus.util.ExcelUtil.isExcel2007;
import static com.auxiliarybus.util.PositionUtil.*;

/**
 * Poi导出Excel
 * <p>
 * Created by wangchaohui on 2019/3/26
 */
public class PoiImport {

    public static List<BusSiteLocation> importLocationExcel() throws Exception {
        File file = new File("F:\\excel\\嘉兴站点经纬度最新.xlsx");
        Workbook wb = null;
        try {
            if (isExcel2007(file.getPath())) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } else {
                wb = new HSSFWorkbook(new FileInputStream(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        List<BusSiteLocation> busSiteLocationList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);//获取索引为i的行，以1开始
            Double line_num = row.getCell(0).getNumericCellValue();
            String siteName = row.getCell(1).getStringCellValue();
            Double longitude = row.getCell(2).getNumericCellValue();
            Double latitude = row.getCell(3).getNumericCellValue();

            Gps gps = gps84_To_Gcj02(longitude, latitude);

            BusSiteLocation busSiteLocation = new BusSiteLocation(i, line_num.longValue(), siteName, String.valueOf(gps.wgLon), String.valueOf(gps.wgLat));

            System.out.println(busSiteLocation);
            busSiteLocationList.add(busSiteLocation);
        }
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return busSiteLocationList;
    }

    static Sheet initSheet(Workbook wb, File file) {
        try {
            if (isExcel2007(file.getPath())) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } else {
                wb = new HSSFWorkbook(new FileInputStream(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        return sheet;
    }


    public static List<Questionnaire> importExcel() throws Exception {
        File file = new File("F:\\excel\\x1.xls");
        Workbook wb = null;
        List<Questionnaire> questionnaireList = new ArrayList<>();
        Sheet sheet = initSheet(wb, file);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);//获取索引为i的行，以1开始
            getLocationFromSurvey(row, i, questionnaireList);
        }
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionnaireList;
    }


    static void getLocationFromSurvey(Row row, int i, List<Questionnaire> questionnaireList) {
        String add = row.getCell(9).getStringCellValue();


        String[] addary = add.split("\\[");

        String name = addary[0];
        String location = addary[1];

        String[] locationary = location.split(",");
        String longitude = locationary[0];
        String latitude = locationary[1].replace("]", "");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(i);
        questionnaire.setName(name);
        questionnaire.setLongitude(longitude);
        questionnaire.setLatitude(latitude);

        System.out.println(questionnaire);
        questionnaireList.add(questionnaire);
    }

}