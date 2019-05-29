package com.auxiliarybus.countOrder;

/**
 * Created by wangch on 2019/5/29
 */
public class OrderSite {

    String sitename;
    int count;


    public OrderSite(String sitename, int count) {
        this.sitename = sitename;
        this.count = count;
    }


    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
