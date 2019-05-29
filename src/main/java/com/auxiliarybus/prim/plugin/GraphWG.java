package com.auxiliarybus.prim.plugin;

import java.util.HashMap;

/**
 * Created by wangch on 2019/4/24
 */
public class GraphWG {
    int edgeNum;
    VertexWG[] vertexArray;

    HashMap<Integer, VertexWG> VertexWGmap = new HashMap<>();

    int verNum;


    public GraphWG(int edgeNum, int verNum) {
        this.edgeNum = edgeNum;
        this.vertexArray = new VertexWG[verNum];
        this.verNum = verNum;
    }


}
