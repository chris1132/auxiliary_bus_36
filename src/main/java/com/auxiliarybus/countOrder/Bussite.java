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

            //����������
            HashMap<String, String> siteMap = new HashMap<>();
            String outorder = "����Դһ���� 1\n" +
                    "����Է(����Է) 2\n" +
                    "����̫���� 1\n" +
                    "���޹�԰(��Ͷ֤ȯ) 2\n" +
                    "��ˮ��ũó�г� 1\n" +
                    "�Ϻ��ƴ����� 1\n" +
                    "��ְԺ�� 1\n" +
                    "��ʢ��԰���� 4\n" +
                    "���ϻ�԰һ�� 1\n" +
                    "�Ͻ�·��Ϫ��· 6\n" +
                    "����ԷС�� 1\n" +
                    "����Ħ����(����ʿ��ǻ���޻���) 4\n" +
                    "����·��ȭ· 2\n" +
                    "����ũó�г��� 2\n" +
                    "�𶼾����� 2\n" +
                    "��ʢ·����· 3\n" +
                    "�������������Ĺ�����Ŧվ 1\n" +
                    "��ˮ·�Ĳ�· 1\n" +
                    "���趼�� 1\n" +
                    "����·���·(��������) 1\n" +
                    "����·����· 1\n" +
                    "���ϻ�԰ 1\n" +
                    "Խ�㱱·��Խ· 2\n" +
                    "��һ�԰�� 2\n" +
                    "��������(ʵ��Сѧ) 2\n" +
                    "����·�ź���· 2\n" +
                    "��̫��Է 2\n" +
                    "���Ϲ�Ԣ 1\n" +
                    "������ѧ 2\n" +
                    "Խ�㱱·����· 1\n" +
                    "����·�ƶ�· 1\n" +
                    "�ʶ���Է 3\n" +
                    "����Ӣ��*����ۡ 2\n" +
                    "�й������ 1\n" +
                    "������ 5\n" +
                    "��ŷ·��ȭ· 4\n" +
                    "��԰·����·(�Ǹ��������ǹ�) 3\n" +
                    "�пƴ����ı� 5\n" +
                    "���»�԰��(���󷢳���) 1\n" +
                    "���·����· 1\n" +
                    "����������Ŧվ 3\n" +
                    "���˹ܾ��� 1\n" +
                    "�м�����߾��� 1\n" +
                    "����·����· 2\n" +
                    "��ˮ·ũó�г� 1";

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
                        System.out.println("["+longitude+","+latitude+","+"\""+name+" "+value+"��\"]," );
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
        // �����ڿ�ʼ1����֮��ÿ��1����ִ��һ��job1
        service.scheduleAtFixedRate(new Schedule(), initialDelay, period, TimeUnit.SECONDS);



    }


}
