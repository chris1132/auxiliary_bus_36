package com.auxiliarybus.prim.plugin;

/**
 * Created by wangch on 2019/4/24
 */
public class VertexWG {

    /**
    * �ڵ�id
    * ��Ӧվ̨id
    * */
    int verid;

    /**
     * �ڵ���
     * ��Ӧվ̨��
     * */
    String verName;

    /**
     * Ȩ��
     * ��Ӧ��վ���ľ���
     * */
    int weight;


    double key;

    /**
     * ��Ӧ��ϵ���վ̨��������
     * */
    int count;
    VertexWG parent;
    VertexWG nextNode;
}
