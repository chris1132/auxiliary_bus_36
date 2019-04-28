package com.auxiliarybus.prim;

import com.auxiliarybus.prim.plugin.BusSiteLocationGD;
import com.auxiliarybus.prim.plugin.CreateWG;
import com.auxiliarybus.prim.plugin.GraphWG;
import com.auxiliarybus.prim.plugin.Prim;
import com.auxiliarybus.prim.plugin.util.JacksonUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangch on 2019/4/24
 */
public class MainFunc {

    /**扇区数  偶数项*/
    private static final int sector_num = 4;


    public static void main(String[] args) {

        /***
         * 作为根节点
         * */
        BusSiteLocationGD originSite = new BusSiteLocationGD(0,0,"嘉兴一中实验南门","120.772816","30.738425");

        HashMap<Integer,List<BusSiteLocationGD>> sectorMap = new HashMap<>();
        for(int i = 1 ;i <=sector_num;i++){
            sectorMap.put(i,new ArrayList<>(List.of(originSite)));
        }


        List<BusSiteLocationGD> totallist = JacksonUtil.str2list(getDatafromFile(), BusSiteLocationGD.class);

        for(BusSiteLocationGD site : totallist){
            var theta =  getTheta(originSite,site);

            if(theta>=0 && theta<90){
                List<BusSiteLocationGD> listPart1 = sectorMap.get(1);
                listPart1.add(site);
                sectorMap.put(1,listPart1);
            }else if(theta>=90 && theta<=180){
                List<BusSiteLocationGD> listPart2 = sectorMap.get(2);
                listPart2.add(site);
                sectorMap.put(2,listPart2);
            }else if(theta<0 && theta>=-90){
                List<BusSiteLocationGD> listPart3 = sectorMap.get(3);
                listPart3.add(site);
                sectorMap.put(3,listPart3);
            }else if(theta<-90 && theta>=-180){
                List<BusSiteLocationGD> listPart4 = sectorMap.get(4);
                listPart4.add(site);
                sectorMap.put(4,listPart4);
            }
        }


        for(int i = 1 ;i <=sector_num;i++) {
            System.out.println("第"+i+"扇区结果：");

            List<BusSiteLocationGD> listPart = sectorMap.get(i);
            //节点数
            int verNum = listPart.size();
            //边数
            int edgeNum = verNum*(verNum-1);

            GraphWG graph=new GraphWG(edgeNum,verNum);
            CreateWG createWG=new CreateWG();

            createWG.initialWg(graph,listPart);
        //        createWG.outputWG(graph);
            Prim prim=new Prim(verNum);
            var res = prim.primSpanningTree(graph);


            System.out.println("——————————————————————————————");
        }

    }


    /**
     * 获取站点数据
     * type：json
     * */
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


    /**
     * 计算角度
     * */
    private static Double getTheta(BusSiteLocationGD originSite,BusSiteLocationGD destinationSite){
        // 将矩形坐标 (x, y) 转换成极坐标 (r, thet));
        //计算弧度
        var angle = Math.atan2(Double.valueOf(originSite.getLongitude())-Double.valueOf(destinationSite.getLongitude()),
                Double.valueOf(originSite.getLatitude())-Double.valueOf(destinationSite.getLatitude()));

        return angle*(180/Math.PI);
    }
}
