package com.auxiliarybus.prim;

import java.util.Scanner;

/**
 * Created by wangch on 2019/4/24
 * @Description 创建图
 */
public class CreateWG {

    double infinity=10.0/0.0;
    /**
     * 根据用户输入的string类型的顶点返回该顶点
     * @param graph 图
     * @param str 输入数据
     * @return返回一个顶点
     */ public static VertexWG getVertex(GraphWG graph,String str){ for(int i=0;i<graph.verNum;i++){ if(graph.vertexArray[i].verName.equals(str)){ return graph.vertexArray[i]; } } return null; } /**
     * 初始化一个无向带权图，并且每个顶点包含parent域和key域
     * @param graph 生成的图
     */
     public void initialWg(GraphWG graph,int verNum,long edgeNum){
         @SuppressWarnings("resource")
         Scanner scan=new Scanner(System.in);
         //顶点数
         graph.verNum=verNum;
         //边数
         graph.edgeNum=edgeNum;
         System.out.println("请依次输入定点名称：");
         for(int i=0;i<graph.verNum;i++){
             VertexWG vertex=new VertexWG();
             String name=scan.next();
             vertex.verName=name;
             vertex.key=infinity;
             vertex.parent=null;
             vertex.nextNode=null;
             graph.vertexArray[i]=vertex;
         }
         System.out.println("请依次输入图的边及权重：");
         for(int i=0;i<graph.edgeNum;i++){
             String preV=scan.next();
             String folV=scan.next();
             int weight=scan.nextInt();
             VertexWG v1=getVertex(graph,preV);

             if(v1==null) System.out.println("输入边在图中没有的顶点！");

             VertexWG v2=new VertexWG();
             v2.verName=folV;
             v2.weight=weight;
             v2.nextNode=v1.nextNode;
             v1.nextNode=v2;
             VertexWG reV2=getVertex(graph,folV);
             if(reV2==null) System.out.println("输入边在图中没有的顶点！");
             VertexWG reV1=new VertexWG();
             reV1.verName=preV;
             reV1.weight=weight;
             reV1.nextNode=reV2.nextNode;
             reV2.nextNode=reV1;
         }
     }

//     public void outputWG(GraphWG graph){
//         System.out.println("输出加权图的邻接链表:");
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
