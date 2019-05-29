package com.auxiliarybus.countOrder;

import com.auxiliarybus.prim.plugin.util.JacksonUtil;
import com.auxiliarybus.util.HttpClientUtil;
import com.auxiliarybus.util.JacksonUtil4J;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.dyuproject.protostuff.CollectionSchema.MessageFactories.List;

/**
 * Created by wangch on 2019/5/28
 */
public class CountOrder {

    static class Schedule implements Runnable{
        @Override
        public void run(){

            String url = "http://www.jkxabus.com/mgt/order/orderMaster/list?orderStatus=1&isAsc=asc";
            String res =HttpClientUtil.sendPost(url,"");

            //����������
            HashMap<String,Integer> weekorderNumMap = new HashMap<>();
            HashMap<String,Order> weeklineMap = new HashMap<>();

            //����
            HashMap<String,Integer> dailyorderNumMap = new HashMap<>();
            HashMap<String,Order> dailylineMap = new HashMap<>();

            HashMap<String,Integer> classnamemap = new HashMap<>();
            HashMap<String,Integer> outordermap = new HashMap<>();
            // 20190524000059   ��С�
            // 20190524000061   ��С�
            // 20190524000128   ������
            // 20190524000169   ������
            // 20190525000017   ����
            //20190528000152,20190528000153,20190528000154,20190528000155,20190528000156,20190528000157,20190528000158,20190528000161,20190528000162,20190528000163,20190528000164
            String outorder = "20190528000164,20190528000152,20190528000153,20190528000154,20190528000155,20190528000156,20190528000157,20190528000158,20190528000161,20190528000162,20190528000163,20190524000128,20190524000169,20190525000017,20190524000059,20190524000061";


            // 20190528000032   ½��¡
            // 20190527000015   ����Զ
            // 20190528000049,20190528000043  ����

            //Ӫ��
            int revenue = 0;
            //���������� ������
            int daily = 0;
            //���� ������
            int week = 0;
            //�ܶ�����
            int total = 0;

            //֧�������׶�
            int alipay = 0;
            int alipaymount = 0;
            //΢�Ž��׶�
            int wxpay = 0;
            int wxpaymount = 0;

            ObjectMapper mapper = new ObjectMapper();
            try {
                String[] outorderAry =outorder.split(",");

                for(String out:outorderAry){
                    outordermap.put(out,1);
                }


                JsonNode node = mapper.readTree(res);
                String resultJson = node.get("rows").toString();
                JsonNode rownode = mapper.readTree(resultJson);

                total = Integer.valueOf(node.get("total").toString());


                for(int i=0;i<=total;i++){

                    String orderNo = rownode.get(i).get("orderNo").toString().replace("\""," ").trim();

                    if(outordermap.get(orderNo)==null) {

                        String scheduledName = rownode.get(i).get("scheduledName").toString().replace("\"", "");
                        String startStopName = rownode.get(i).get("startStopName").toString().replace("\"", "");
                        String endStopName = rownode.get(i).get("endStopName").toString().replace("\"", "");
                        String startTime = rownode.get(i).get("startTime").toString().replace("\"", "");
                        String className = rownode.get(i).get("className").toString().replace("\"", "");
                        Integer classnum = classnamemap.get(className);
                        if(classnum==null){
                            classnamemap.put(className,1);
                        }else{
                            classnum+=1;
                            classnamemap.replace(className,classnum);
                        }

                        int payAmount = Integer.valueOf(rownode.get(i).get("payAmount").toString());
                        int paytype = Integer.valueOf(rownode.get(i).get("payType").toString());
                        if(1==paytype){
                            wxpay+=1;
                            wxpaymount+=payAmount/100;
                        }
                        if(2==paytype){
                            alipay+=1;
                            alipaymount+=payAmount/100;
                        }

                        Order order = new Order(orderNo,scheduledName, startStopName, endStopName, payAmount, startTime);

                        if (startTime.equals("15:20:00") || startTime.equals("16:10:00")) {
                            packageMap(weekorderNumMap,weeklineMap,order,scheduledName);
                            week =week+ 1;
                        }
                        if (startTime.equals("21:05:00") || startTime.equals("21:35:00")) {
                            packageMap(dailyorderNumMap,dailylineMap,order,scheduledName);
                            daily=daily+1;
                        }
                    }
                }
//                list2 = list2.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
//                for(String order:list2) {
//                    System.out.println(order);
//                }


            } catch (Exception e) {
            }
            System.out.println("\n\n");
            Date date =new Date();
            System.out.println((date.getMonth()+1)+"��"+date.getDate()+"�� "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
            System.out.println("�Ѹ��"+(total-outordermap.size())+"�� ����׿�ˣ�"+alipay+"��  С����"+wxpay+"����");
            System.out.println("Ʊ��Ӫ�գ�"+(wxpaymount+alipaymount)+"Ԫ  ��֧������"+alipaymount+"Ԫ ΢�ţ�"+wxpaymount+"Ԫ��");

            System.out.println("________________________________________");
            printinfo(weekorderNumMap,weeklineMap,"�������繲��"+week+"��");

            System.out.println("________________________________________");
            printinfo(dailyorderNumMap,dailylineMap,"�������������ϣ�"+daily+"��");
            System.out.println("________________________________________");
            printclassinfo(classnamemap);
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

    private static void packageMap(HashMap<String,Integer> numMap,HashMap<String,Order> lineMap,Order order,String scheduledName){

        Integer num = numMap.get(scheduledName);
        if (num != null) {
            num += 1;
            numMap.replace(scheduledName, num);
        } else {
            numMap.put(scheduledName, 1);
        }

        Order lineorder = lineMap.get(scheduledName);
        if (lineorder == null) {
            lineMap.put(scheduledName, order);
        }
    }

     private static  void printinfo(HashMap<String,Integer> weekorderNumMap,HashMap<String,Order> weeklineMap,String info){
        System.out.println(info);
        List<Order> list = new ArrayList<Order>();
        for(Map.Entry entry: weekorderNumMap.entrySet()){
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            Order order = weeklineMap.get(key);
            order.setPeopleocunt(Integer.valueOf(value));

            list.add(order);

        }

         list = list.stream().sorted(Comparator.comparing(Order::getScheduledName)).collect(Collectors.toList());
        for(Order order:list){
            StringBuffer sb = new StringBuffer();
            sb.append(order.getScheduledName()).append("  ").
                    append(order.getStartTime()).
                    append(order.getStartStopName()).
                    append("->").append(order.getEndStopName()).
                    append(":").append(order.getPeopleocunt()).append("��");
            System.out.println(sb.toString());
        }
    }

    private static  void printclassinfo(HashMap<String,Integer> classnamemap){

        List<StuClass> list = new ArrayList<StuClass>();
        for(Map.Entry entry: classnamemap.entrySet()){
            String key = entry.getKey().toString();
            int value = Integer.valueOf(entry.getValue().toString());
            StuClass stuclass = new StuClass(key,value);
            list.add(stuclass);

        }

        list = list.stream().sorted(Comparator.comparing(StuClass::getClassname)).collect(Collectors.toList());
        for(StuClass stu:list){
            StringBuffer sb = new StringBuffer();
            if(stu.getClassname().equals("null")){
                sb.append("û�а༶��Ϣ:")
                        .append(stu.getCount())
                        .append("��  20190527000083 ������,20190528000015 ���ú� ");
            }else if(stu.getClassname().equals("��һ��2����")){
                sb.append(stu.getClassname())
                        .append(":")
                        .append(stu.getCount())
                        .append("��  ")
                        .append("20190527000188  ������ ");
            }else{
                sb.append(stu.getClassname()).
                        append(":")
                        .append(stu.getCount())
                        .append("��");
            }
            System.out.println(sb.toString());
        }
    }
}
