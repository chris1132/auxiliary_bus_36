package com.auxiliarybus.prim;

import java.util.Scanner;

/**
 * Created by wangch on 2019/4/24
 * @Description ����ͼ
 */
public class CreateWG {

    double infinity=10.0/0.0;
    /**
     * �����û������string���͵Ķ��㷵�ظö���
     * @param graph ͼ
     * @param str ��������
     * @return����һ������
     */ public static VertexWG getVertex(GraphWG graph,String str){ for(int i=0;i<graph.verNum;i++){ if(graph.vertexArray[i].verName.equals(str)){ return graph.vertexArray[i]; } } return null; } /**
     * ��ʼ��һ�������Ȩͼ������ÿ���������parent���key��
     * @param graph ���ɵ�ͼ
     */
     public void initialWg(GraphWG graph,int verNum,long edgeNum){
         @SuppressWarnings("resource")
         Scanner scan=new Scanner(System.in);
         //������
         graph.verNum=verNum;
         //����
         graph.edgeNum=edgeNum;
         System.out.println("���������붨�����ƣ�");
         for(int i=0;i<graph.verNum;i++){
             VertexWG vertex=new VertexWG();
             String name=scan.next();
             vertex.verName=name;
             vertex.key=infinity;
             vertex.parent=null;
             vertex.nextNode=null;
             graph.vertexArray[i]=vertex;
         }
         System.out.println("����������ͼ�ı߼�Ȩ�أ�");
         for(int i=0;i<graph.edgeNum;i++){
             String preV=scan.next();
             String folV=scan.next();
             int weight=scan.nextInt();
             VertexWG v1=getVertex(graph,preV);

             if(v1==null) System.out.println("�������ͼ��û�еĶ��㣡");

             VertexWG v2=new VertexWG();
             v2.verName=folV;
             v2.weight=weight;
             v2.nextNode=v1.nextNode;
             v1.nextNode=v2;
             VertexWG reV2=getVertex(graph,folV);
             if(reV2==null) System.out.println("�������ͼ��û�еĶ��㣡");
             VertexWG reV1=new VertexWG();
             reV1.verName=preV;
             reV1.weight=weight;
             reV1.nextNode=reV2.nextNode;
             reV2.nextNode=reV1;
         }
     }

//     public void outputWG(GraphWG graph){
//         System.out.println("�����Ȩͼ���ڽ�����:");
//         for(int i=0;i<graph.verNum;i++){
//             VertexWG vertex=graph.vertexArray[i];
//             System.out.print(vertex.verName);
//             VertexWG current=vertex.nextNode;
//             while(current!=null){
//                 System.out.print("-->"+current.verName+"("+current.weight+")");
//                 current=current.nextNode;
//             }
//             System.out.println();
//         }
//     }

}
