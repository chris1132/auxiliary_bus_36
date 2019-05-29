package com.auxiliarybus.util;

import org.apache.poi.ss.usermodel.*;

import java.text.DecimalFormat;

/**
 * Created by wangch on 2019/3/26
 */
public class ExcelUtil {


    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }


    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


    public static String getStringFromNumericCell(Cell cell) {
        return new DecimalFormat("#").format(cell.getNumericCellValue());
    }

    public static CellStyle setSimpleCellBorder(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    public static void inputCell(Row row, int index, String value, CellStyle style) {
        Cell cell = row.createCell(index);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

}
