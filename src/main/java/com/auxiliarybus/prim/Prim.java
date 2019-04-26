package com.auxiliarybus.prim;


/**
 * Created by wangch on 2019/4/24
 */
public class Prim {
    int currentSize=0;
    int maxSize=0;
    VertexWG[] minHeap;

    public Prim() {}

    public Prim(int verNum) {
        minHeap = new VertexWG[verNum];
    }

    /**
     * ͨ��weight������EdgeNodeΪ�ڵ����С��
     * @param_edgeNode Ϊ��Ȩ�ı߼�
     */
    public void createMinHeap(VertexWG[] verArray){
         currentSize=verArray.length;
         maxSize=minHeap.length;
         if(currentSize>=maxSize){
             maxSize*=2;
             minHeap=new VertexWG[maxSize];
         }
         for(int i=0;i<currentSize;i++)
             minHeap[i+1]=verArray[i];
         double y; int c;
         for(int i=currentSize/2;i>=1;i--){
             VertexWG ver=minHeap[i];
             y=ver.key;
             c=2*i;
             while(c<=currentSize){
                 if(c<currentSize && minHeap[c].key>minHeap[c+1].key) c++;
                 if(minHeap[c].key>=y) break;
                 minHeap[c/2]=minHeap[c];
                 c=c*2;
             }
             minHeap[c/2]=ver;
         }
    }
    /**
     * ��С��ɾ������˼·��һ�ֺ�ǰ��һ��������һֱ���ٷ��ڸ��ڵ���Ǹ����һ���ڵ����ղ����λ��
     * ��һ��˼·����ÿһ����������Ľ���Ȼ����һһ���ڽ���ͬ������
     */
    public VertexWG deleteMinHeap(){
        if(currentSize<1) System.out.println("���Ѿ�Ϊ�գ��޷�ִ��ɾ��");
        VertexWG ver=minHeap[1];
        minHeap[1]=minHeap[currentSize];
        currentSize-=1; int c=2,j=1;
        VertexWG ver1=minHeap[currentSize+1];
        while(c<=currentSize){
            if(c<currentSize && minHeap[c].key>minHeap[c+1].key) c++;
            if(ver1.key<=minHeap[c].key) break;
            minHeap[j]=minHeap[c];
            j=c;
            c=c*2;
        }
        minHeap[j]=ver1;
        return ver;
    }
    /**
     * �����ﷵ��minHeap�еĶ������
     * @param name ���������
     * @return����minHeap�е�һ���������
     */
    public VertexWG getHeapVertex(String name){
        for(int i=1;i<=currentSize;i++){
            if(minHeap[i].verName.equals(name))
                return minHeap[i];
        }
        return null;
    } /**
     * MST��Prim�㷨����ʵ�ֺ���
     * @param graph ͼ
     */
    public void primSpanningTree(GraphWG graph){
        VertexWG verRoot=CreateWG.getVertex(graph,0);

        verRoot.key=0;
        VertexWG[] verArray=new VertexWG[graph.verNum];
        for(int i = 0; i<graph.verNum; i++){
            verArray[i]=graph.vertexArray[i];
        }
        createMinHeap(verArray);
        System.out.println("����prim�㷨������С������,�Ӽ���һ�г�����˳��Ϊ:");
        while(currentSize>=1){
            //minHeap�еĶ����graph.vertexArray�еĲ���ͬһ������ //
            // minHeaph�еĶ����Ѿ����²����Ķ�����
            // ����ע��һ��u�������,u��Ȼ��minHeap�еĶ���
            VertexWG[] vArray=new VertexWG[currentSize];
            for(int i=0;i<currentSize;i++){
                vArray[i]=minHeap[i+1];
            }
            //������Ҫע�⣬ÿ��ɾ���ڵ������keyֵ֮����Ҫ�����µ�keyֵ���¹�����С��
            createMinHeap(vArray);
            VertexWG u=deleteMinHeap();

            if(u.count>0)System.out.println("--->"+u.verName+"������վ��  "+u.count+"����");

            VertexWG current=u.nextNode;
            //ע������whileѭ���е�������ͬ�Ķ������
            // currentNow��current
            while(current!=null){
                VertexWG currentNow=getHeapVertex(current.verName);
                if(currentNow!=null && current.weight<currentNow.key){
                    currentNow.parent=u;
                    currentNow.key=current.weight;
                }
                current=current.nextNode;
            }
        }
    }



}
