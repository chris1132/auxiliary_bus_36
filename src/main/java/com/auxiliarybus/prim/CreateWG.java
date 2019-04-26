package com.auxiliarybus.prim;



import com.auxiliarybus.entity.BusSiteLocation;

import java.util.List;

import static com.auxiliarybus.prim.DistanceComputerUtil.getDistance;

/**
 * Created by wangch on 2019/4/24
 * @Description ����ͼ
 */
public class CreateWG {

    double infinity=10.0/0.0;
    /**
     * ��ʼ��һ�������Ȩͼ������ÿ���������parent���key��
     * @param graph ���ɵ�ͼ
     */
     public void initialWg(GraphWG graph, List<BusSiteLocation> list){


         //�����ʼ��
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
             //��ֵͼ�ı߼�Ȩ��
             for (int j = 0; j < graph.verNum && j != k; j++) {
                 BusSiteLocation preVQues = list.get(k);
                 BusSiteLocation folVQues = list.get(j);

                 /**��γ�ȼ���ֱ�߾����Ȩ��*/
                 //TODO ���ðٶ�api����·������
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
     * ���ݾ�γ�ȼ���·��
     * */
     public int getDis(BusSiteLocation preVQues,BusSiteLocation folVQues){
         return (int)getDistance(Double.valueOf(preVQues.getLongitude()),
                 Double.valueOf(preVQues.getLatitude()),
                 Double.valueOf(folVQues.getLongitude()),
         Double.valueOf(folVQues.getLatitude()));
     }

    /**
     * �����û������string���͵Ķ��㷵�ظö���
     * @param graph ͼ
     * @param id ��������
     * @return����һ������
     */
    public static VertexWG getVertex(GraphWG graph,int id){
        return graph.VertexWGmap.get(id);
    }

    public void outputWG(GraphWG graph){
        System.out.println("�����Ȩͼ���ڽ�����:");
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
