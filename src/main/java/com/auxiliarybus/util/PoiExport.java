package com.auxiliarybus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import com.auxiliarybus.entity.BusSiteLocation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static com.auxiliarybus.util.ExcelUtil.isExcel2007;

/**
 * Poiµ¼³öExcel
 * Created by wangchaohui on 2019/3/26
 */
public class PoiExport {
    public static void exportHeroInfo(List<BusSiteLocation> busSiteLocationList, String name) {
        String templetFilePath = "F:\\excel\\model.xlsx";
        String exportFilePath = "F:\\excel\\" + name;

        try {
            File exportFile = new File(exportFilePath);
            File templetFile = new File(templetFilePath);
            Workbook workBook;

            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(exportFile);
            FileInputStream fis = new FileInputStream(templetFile);
            if (isExcel2007(templetFile.getPath())) {
                workBook = new XSSFWorkbook(fis);
            } else {
                workBook = new HSSFWorkbook(fis);
            }

            Sheet sheet = workBook.getSheetAt(0);

            int rowIndex = 1;
            for (BusSiteLocation item : busSiteLocationList) {
                Row row = sheet.createRow(rowIndex);
                row.createCell(0).setCellValue(item.getSiteName());
                row.createCell(1).setCellValue(item.getLongitude());
                row.createCell(2).setCellValue(item.getLatitude());
                row.createCell(3).setCellValue(item.getSurveyCount());
                rowIndex++;
            }

            workBook.write(out);
            out.flush();
            out.close();

            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}