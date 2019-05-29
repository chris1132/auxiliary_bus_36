package com.auxiliarybus.countOrder;

import com.auxiliarybus.entity.BusSiteLocation;
import com.auxiliarybus.util.entity.Gps;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.auxiliarybus.util.ExcelUtil.isExcel2007;
import static com.auxiliarybus.util.PositionUtil.gps84_To_Gcj02;

/**
 * Created by wangch on 2019/5/29
 */
public class ExcelUtil {

//    public static void main(String[] args) throws Exception {
//        importLocationExcel();
//    }

    public static HashMap<String, HashMap<String,Integer>> importLocationExcel() throws Exception {

        HashMap<String,Integer> map1 = new HashMap<>();
        HashMap<String,Integer> map2 = new HashMap<>();
        HashMap<String,Integer> map3 = new HashMap<>();
        HashMap<String,Integer> map4 = new HashMap<>();

        HashMap<String, HashMap<String,Integer>> ordersitemap = new HashMap<>();


        File file = new File("F:\\excel\\order.xlsx");
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
            String start_time = row.getCell(7).getStringCellValue().trim();
            String board = row.getCell(11).getStringCellValue().trim();

            if (start_time.equals("15:20:00")) {
                Integer count1 = map1.get(board);
                if(count1!=null){
                    count1+=1;
                    map1.replace(board,count1);
                }else{
                    map1.put(board,1);
                }
            }
            if(start_time.equals("16:10:00")){
                Integer count1 = map2.get(board);
                if(count1!=null){
                    count1+=1;
                    map2.replace(board,count1);
                }else{
                    map2.put(board,1);
                }
            }
            if (start_time.equals("21:05:00") ) {
                Integer count1 = map3.get(board);
                if(count1!=null){
                    count1+=1;
                    map3.replace(board,count1);
                }else{
                    map3.put(board,1);
                }
            }
            if ( start_time.equals("21:35:00")) {
                Integer count1 = map4.get(board);
                if(count1!=null){
                    count1+=1;
                    map4.replace(board,count1);
                }else{
                    map4.put(board,1);
                }
            }
        }
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordersitemap.put("5_1520",map1);
        ordersitemap.put("5_1610",map2);
        ordersitemap.put("74_2105",map3);
        ordersitemap.put("74_2135",map4);
        return ordersitemap;
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
}
