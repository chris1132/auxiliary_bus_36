package com.auxiliarybus.countOrder;

import com.auxiliarybus.util.HttpClientUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.auxiliarybus.countOrder.ExcelUtil.importLocationExcel;

/**
 * Created by wangch on 2019/5/29
 */
public class CountSite {


    public static void main(String[] args)throws Exception {
        var outmap2135 = Map.of("紫竹名苑南",1,"金都九月洋房",2,"嘉州美都(实验小学)",1);
        var outmap2105 = Map.of("嘉兴汽车北站南",1,"锦绣农贸市场东",1,"秀州中学",1);



        HashMap<String, String> sitemap = getsitemap();
        HashMap<String, HashMap<String,Integer>> map = importLocationExcel();

        HashMap<String,Integer> sitecountmap= map.get("5_1520");
        for(Map.Entry entry: sitecountmap.entrySet()) {
            String key = entry.getKey().toString();
                String lnlat = sitemap.get(key);
                String value = entry.getValue().toString();
                System.out.println("[" + lnlat + "," + "\"" + key + " " + value + "人\"],");

        }

//        HashMap<String,Integer> sitecountmap= map.get("5_1610");
//        for(Map.Entry entry: sitecountmap.entrySet()) {
//            String key = entry.getKey().toString();
//                String lnlat = sitemap.get(key);
//                String value = entry.getValue().toString();
//                System.out.println("[" + lnlat + "," + "\"" + key + " " + value + "人\"],");
//
//        }
//
//
//        HashMap<String,Integer> sitecountmap= map.get("74_2105");
//        for(Map.Entry entry: sitecountmap.entrySet()) {
//            String key = entry.getKey().toString();
//            if(outmap2105.get(key)==null) {
//                String lnlat = sitemap.get(key);
//                String value = entry.getValue().toString();
//
//                System.out.println("[" + lnlat + "," + "\"" + key + " " + value + "人\"],");
//            }
//        }
//
//
//        HashMap<String,Integer> sitecountmap= map.get("74_2135");
//        for(Map.Entry entry: sitecountmap.entrySet()) {
//            String key = entry.getKey().toString();
//            if(outmap2135.get(key)==null) {
//                String lnlat = sitemap.get(key);
//                String value = entry.getValue().toString();
//
//                System.out.println("[" + lnlat + "," + "\"" + key + " " + value + "人\"],");
//            }
//        }




    }

    public static HashMap getsitemap(){
        String url = "http://www.jkxabus.com/mgt/system/stop/list";
        String res = HttpClientUtil.sendPost(url, "");
        HashMap<String, String> siteMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        int total=0;
        try {

            JsonNode node = mapper.readTree(res);
            String resultJson = node.get("rows").toString();
            JsonNode rownode = mapper.readTree(resultJson);
            total = Integer.valueOf(node.get("total").toString());
            for(int i=0;i<total;i++) {
                String name = rownode.get(i).get("name").toString().replace("\"", "");
                if(siteMap.get(name)==null) {
                    String latitude = rownode.get(i).get("latitude").toString().replace("\"", "");
                    String longitude = rownode.get(i).get("longitude").toString().replace("\"", "");
                    siteMap.put(name,longitude+","+latitude);
                }
            }
        }catch (Exception e){}
        return siteMap;
    }
}
