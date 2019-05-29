package com.auxiliarybus.LRU;

import java.util.Scanner;

/**
 * Created by wangch on 2019/5/14
 */
public class MainTest {

    public static int MAX_CACHE = 10;

    public static void main(String[] args) {
        LRUNode headNode = new LRUNode();

        int num = 10;
        initLRUNode(headNode,num);

        Scanner scan = new Scanner(System.in);
        String keyVal = scan.next();

        boolean findkey = false;

        LRUNode lru = headNode;
        if(lru.key.equals(keyVal)){
            System.out.println(lru.data);
            findkey = true;
        }
        int index = 1;
        //链表里寻找节点，如果找到，该节点移到最前面
        while(lru.nextnode!=null && !findkey){
            LRUNode lru2 = lru.nextnode;
            if(lru2.key.equals(keyVal)){
                System.out.println(lru2.data);
                lru.nextnode = lru2.nextnode;
                lru2.nextnode = headNode;
                headNode = lru2;
                findkey= true;
            }else{
                if(lru2.nextnode == null){
                    lru.nextnode = null;
                }else{
                    lru = lru.nextnode;
                }
            }

        }
        if(!findkey){
            //如果没有找到，则在链表的头部插入
            LRUNode newnode = new LRUNode();
            newnode.key = ""+keyVal;
            newnode.data = "da："+keyVal;

            newnode.nextnode = headNode;
            headNode = newnode;
        }


        System.out.println("new linklist---------");
        while(headNode!=null){
            System.out.println(headNode.data);
            headNode = headNode.nextnode;
        }

    }

    public static void initLRUNode(LRUNode headNode,int num){

        headNode.key = "1";
        headNode.data = "da：1";
        headNode.nextnode = null;


        LRUNode current = headNode;

        for(int i= 2; i<=num ; i++){
            LRUNode lruNode = new LRUNode();
            lruNode.key = i+"";
            lruNode.data = "da:"+i;
            lruNode.nextnode = null;

            current.nextnode = lruNode;

            current = lruNode;
        }
    }


}
