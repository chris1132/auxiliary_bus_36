package com.auxiliarybus.prim;



import com.auxiliarybus.entity.BusSiteLocation;

import java.util.List;

import static com.auxiliarybus.prim.DistanceComputerUtil.getDistance;

/**
 * Created by wangch on 2019/4/24
 * @Description 创建图
 */
public class CreateWG {

    double infinity=10.0/0.0;
    /**
     * 初始化一个无向带权图，并且每个顶点包含parent域和key域
     * @param graph 生成的图
     */
     public void initialWg(GraphWG graph, List<BusSiteLocation> list){


         //顶点初始化
         for(int i=0;i<graph.verNum;i++){
             VertexWG vertex=new VertexWG();
             vertex.verid = list.get(i).getId();
             vertex.verName=list.get(i).getSiteName();
             vertex.key=infinity;
             vertex.count = list.get(i).getSurveyCount();
             vertex.parent=null;
             vertex.nextNode=null;
             graph.vertexArray[i]=vertex;
             graph.VertexWGmap.put(vertex.verid,vertex);
         }

         for(int k=0;k<graph.verNum;k++) {
             //赋值图的边及权重
             for (int j = 0; j < graph.verNum && j != k; j++) {
                 BusSiteLocation preVQues = list.get(k);
                 BusSiteLocation folVQues = list.get(j);

                 /**经纬度计算直线距离给权重*/
                 //TODO 调用百度api计算路径长度
                 int weight = getDis(preVQues,folVQues);


                 createEdge( graph, preVQues.getId(),weight,folVQues.getSiteName());
                 createEdge( graph, folVQues.getId(),weight,preVQues.getSiteName());

//                 VertexWG v1 = getVertex(graph, preVQues.getId());
//                 VertexWG v2 = new VertexWG();
//                 v2.verName = folVQues.getSiteName();
//                 v2.weight = weight;
//                 v2.nextNode = v1.nextNode;
//                 v1.nextNode = v2;
//
//
//                 VertexWG reV2 = getVertex(graph, folVQues.getId());
//                 VertexWG reV1 = new VertexWG();
//                 reV1.verName = preVQues.getSiteName();
//                 reV1.weight = weight;
//                 reV1.nextNode = reV2.nextNode;
//                 reV2.nextNode = reV1;
             }
         }
     }

     public void createEdge(GraphWG graph,int id,int weight,String verName){
         VertexWG v1 = getVertex(graph, id);
         VertexWG v2 = new VertexWG();
         v2.verName = verName;
         v2.weight = weight;
         v2.nextNode = v1.nextNode;
         v1.nextNode = v2;
     }

    /**
     * 根据经纬度计算路程
     * */
     public int getDis(BusSiteLocation preVQues,BusSiteLocation folVQues){
         return (int)getDistance(Double.valueOf(preVQues.getLongitude()),
                 Double.valueOf(preVQues.getLatitude()),
                 Double.valueOf(folVQues.getLongitude()),
         Double.valueOf(folVQues.getLatitude()));
     }

    /**
     * 根据用户输入的string类型的顶点返回该顶点
     * @param graph 图
     * @param id 输入数据
     * @return返回一个顶点
     */
    public static VertexWG getVertex(GraphWG graph,int id){
        return graph.VertexWGmap.get(id);
    }

    public void outputWG(GraphWG graph){
        System.out.println("输出加权图的邻接链表:");
        for(int i=0;i<graph.verNum;i++){
            VertexWG vertex=graph.vertexArray[i];
            System.out.print(vertex.verName);
            VertexWG current=vertex.nextNode;
            while(current!=null){
                System.out.print("-->"+current.verName+"("+current.weight+")");
                current=current.nextNode;
            }
            System.out.println();
        }
    }


}
