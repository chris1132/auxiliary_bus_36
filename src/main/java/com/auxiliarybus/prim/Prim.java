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
     * 通过weight构建以EdgeNode为节点的最小堆
     * @param_edgeNode 为带权的边集
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
     * 最小堆删除两种思路，一种和前面一样，就是一直跟踪放在根节点的那个最后一个节点最终插入的位置
     * 另一种思路便是每一次完成完整的交换然后下一一层在进行同样处理
     */
    public VertexWG deleteMinHeap(){
        if(currentSize<1) System.out.println("堆已经为空！无法执行删除");
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
     * 从这里返回minHeap中的顶点对象
     * @param name 定点的名字
     * @return返回minHeap中的一个顶点对象
     */
    public VertexWG getHeapVertex(String name){
        for(int i=1;i<=currentSize;i++){
            if(minHeap[i].verName.equals(name))
                return minHeap[i];
        }
        return null;
    } /**
     * MST的Prim算法具体实现函数
     * @param graph 图
     */
    public void primSpanningTree(GraphWG graph){
        VertexWG verRoot=CreateWG.getVertex(graph,0);

        verRoot.key=0;
        VertexWG[] verArray=new VertexWG[graph.verNum];
        for(int i = 0; i<graph.verNum; i++){
            verArray[i]=graph.vertexArray[i];
        }
        createMinHeap(verArray);
        System.out.println("利用prim算法生成最小生成树,从嘉兴一中出发的顺序为:");
        while(currentSize>=1){
            //minHeap中的对象和graph.vertexArray中的不是同一个对象 //
            // minHeaph中的对象已经是新产生的对象了
            // 这里注意一下u这个对象,u自然是minHeap中的对象
            VertexWG[] vArray=new VertexWG[currentSize];
            for(int i=0;i<currentSize;i++){
                vArray[i]=minHeap[i+1];
            }
            //这里需要注意，每次删除节点更新完key值之后，需要利用新的key值重新构建最小堆
            createMinHeap(vArray);
            VertexWG u=deleteMinHeap();

            if(u.count>0)System.out.println("--->"+u.verName+"（公交站）  "+u.count+"个人");

            VertexWG current=u.nextNode;
            //注意下面while循环中的两个不同的顶点对象：
            // currentNow、current
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
