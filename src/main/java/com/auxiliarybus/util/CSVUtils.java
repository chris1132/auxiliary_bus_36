package com.auxiliarybus.util;

import com.auxiliarybus.entity.BusSiteLocation;
import org.apache.poi.ss.formula.functions.T;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangch on 2019/4/28
 */
public class CSVUtils {

    public static String get18code(int id) {
        if (id > 0 && id < 10) {
            return "JXFZ00000" + id;
        } else if (id >= 10 && id < 100) {
            return "JXFZ0000" + id;
        } else if (id >= 100 && id < 1000) {
            return "JXFZ000" + id;
        } else if (id >= 1000 && id < 10000) {
            return "JXFZ00" + id;
        }
        return "";
    }

    /**
     * 导出
     *
     * @param file     csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
    public static boolean exportCsv(File file, List<BusSiteLocation> dataList) {
        boolean isSucess = false;
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (BusSiteLocation data : dataList) {
                    String name = data.getSiteName();
                    if (name.length() > 9) {
                        name = data.getSiteName().substring(0, 9);
                    }
                    bw.append(get18code(data.getId()))
                            .append(";")
                            .append(name)
                            .append(";4;")
                            .append(data.getLongitude() + "," + data.getLatitude())
                            .append(";")
                            .append(data.getSurveyCount()+"")
                            .append("\r");
                }
            }
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.append("EOF");
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(File file) {
        List<String> dataList = new ArrayList<String>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataList;
    }
}
