package com.auxiliarybus.countOrder;

import com.auxiliarybus.util.HttpClientUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by wangch on 2019/5/28
 */
public class Bussite {

    static class Schedule implements Runnable{
        @Override
        public void run() {

            String url = "http://www.jkxabus.com/mgt/system/stop/list";
            String res = HttpClientUtil.sendPost(url, "");

            //周日至周四
            HashMap<String, String> siteMap = new HashMap<>();
            String outorder = "春晓源一期南 1\n" +
                    "长中苑(海棠苑) 2\n" +
                    "江南太阳城 1\n" +
                    "秀洲公园(中投证券) 2\n" +
                    "三水湾农贸市场 1\n" +
                    "南湖科创中心 1\n" +
                    "嘉职院东 1\n" +
                    "昌盛花园西区 4\n" +
                    "府南花园一区 1\n" +
                    "南江路南溪东路 6\n" +
                    "云洲苑小区 1\n" +
                    "江南摩尔西(牙博士口腔秀洲机构) 4\n" +
                    "翠柳路由拳路 2\n" +
                    "锦绣农贸市场东 2\n" +
                    "金都九月洋房 2\n" +
                    "龙盛路秀清路 3\n" +
                    "秀洲区行政中心公交枢纽站 1\n" +
                    "吉水路文昌路 1\n" +
                    "巴黎都市 1\n" +
                    "广益路庆丰路(宁波银行) 1\n" +
                    "东塔路景湖路 1\n" +
                    "城南花园 1\n" +
                    "越秀北路洪越路 2\n" +
                    "万家花园南 2\n" +
                    "嘉州美都(实验小学) 2\n" +
                    "望鹤路放鹤洲路 2\n" +
                    "亚太花苑 2\n" +
                    "中南公寓 1\n" +
                    "秀州中学 2\n" +
                    "越秀北路洪兴路 1\n" +
                    "望湖路云东路 1\n" +
                    "皇都花苑 3\n" +
                    "格兰英郡*铂金府邸 2\n" +
                    "市广电中心 1\n" +
                    "罗马都市 5\n" +
                    "亚欧路由拳路 4\n" +
                    "秀园路洪贤路(智富城领秀智谷) 3\n" +
                    "市科创中心北 5\n" +
                    "华新花园南(大润发超市) 1\n" +
                    "庆丰路塘桥路 1\n" +
                    "公交东场枢纽站 3\n" +
                    "市运管局西 1\n" +
                    "市检验检疫局西 1\n" +
                    "紫竹路城南路 2\n" +
                    "吉水路农贸市场 1";

            int total=0;
            String[] siteary = outorder.split("\n");
            for(String site:siteary){
                String[] ary=site.split(" ");
                siteMap.put(ary[0],ary[1]);
            }



            ObjectMapper mapper = new ObjectMapper();
            try {

                JsonNode node = mapper.readTree(res);
                String resultJson = node.get("rows").toString();
                JsonNode rownode = mapper.readTree(resultJson);
                total = Integer.valueOf(node.get("total").toString());
                for(int i=0;i<total;i++) {

                    String name = rownode.get(i).get("name").toString().replace("\"", "");
                    if(siteMap.get(name)!=null) {
                        String value =siteMap.get(name);
                        String latitude = rownode.get(i).get("latitude").toString().replace("\"", "");
                        String longitude = rownode.get(i).get("longitude").toString().replace("\"", "");
                        siteMap.replace(name,longitude+","+latitude);
                        System.out.println("["+longitude+","+latitude+","+"\""+name+" "+value+"人\"]," );
                    }
                }
            }catch (Exception e){}



        }
    }


    private ExecutorService executors = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        long initialDelay = 1;
        long period = 500;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        service.scheduleAtFixedRate(new Schedule(), initialDelay, period, TimeUnit.SECONDS);



    }


}
