package com.auxiliarybus.prim.plugin;

/**
 * Created by wangch on 2019/4/24
 */
public class VertexWG {

    /**
    * 节点id
    * 对应站台id
    * */
    int verid;

    /**
     * 节点名
     * 对应站台名
     * */
    String verName;

    /**
     * 权重
     * 对应两站点间的距离
     * */
    int weight;


    double key;

    /**
     * 对应拟合到该站台的需求数
     * */
    int count;
    VertexWG parent;
    VertexWG nextNode;
}
