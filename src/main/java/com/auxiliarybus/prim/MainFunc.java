package com.auxiliarybus.prim;

import com.auxiliarybus.entity.BusSiteLocation;
import com.auxiliarybus.util.JacksonUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by wangch on 2019/4/24
 */
public class MainFunc {


    public static void main(String[] args) {

        List<BusSiteLocation> list = JacksonUtil.str2list(getDatafromFile(), BusSiteLocation.class);


        int verNum = list.size();
        //±ßÊý
        int edgeNum = verNum*(verNum-1);

        GraphWG graph=new GraphWG(edgeNum,verNum);
        CreateWG createWG=new CreateWG();

        createWG.initialWg(graph,list);
//        createWG.outputWG(graph);
        Prim prim=new Prim(verNum);
        prim.primSpanningTree(graph);

    }


    private static String getDatafromFile() {

        String Path="F:\\excel\\busSiteLocationJson.json";
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}
