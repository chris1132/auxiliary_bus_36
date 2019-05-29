package com.auxiliarybus.countOrder;

/**
 * Created by wangch on 2019/5/28
 */
public class StuClass {

    String classname;
    int count;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public StuClass(String classname, int count) {
        this.classname = classname;
        this.count = count;
    }

    @Override
    public String toString() {
        return "StuClass{" +
                "classname='" + classname + '\'' +
                ", count=" + count +
                '}';
    }
}
