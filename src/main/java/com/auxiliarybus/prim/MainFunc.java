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

    /**������  ż����*/
    private static final int sector_num = 4;


    public static void main(String[] args) {

        /***
         * ��Ϊ���ڵ�
         * */
        BusSiteLocationGD originSite = new BusSiteLocationGD(0,0,"����һ��ʵ������","120.772816","30.738425");

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
            System.out.println("��"+i+"���������");

            List<BusSiteLocationGD> listPart = sectorMap.get(i);
            //�ڵ���
            int verNum = listPart.size();
            //����
            int edgeNum = verNum*(verNum-1);

            GraphWG graph=new GraphWG(edgeNum,verNum);
            CreateWG createWG=new CreateWG();

            createWG.initialWg(graph,listPart);
        //        createWG.outputWG(graph);
            Prim prim=new Prim(verNum);
            var res = prim.primSpanningTree(graph);


            System.out.println("������������������������������������������������������������");
        }

    }


    /**
     * ��ȡվ������
     * type��json
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
     * ����Ƕ�
     * */
    private static Double getTheta(BusSiteLocationGD originSite,BusSiteLocationGD destinationSite){
        // ���������� (x, y) ת���ɼ����� (r, thet));
        //���㻡��
        var angle = Math.atan2(Double.valueOf(originSite.getLongitude())-Double.valueOf(destinationSite.getLongitude()),
                Double.valueOf(originSite.getLatitude())-Double.valueOf(destinationSite.getLatitude()));

        return angle*(180/Math.PI);
    }
}
