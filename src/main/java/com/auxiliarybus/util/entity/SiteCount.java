package com.auxiliarybus.util.entity;

/**
 * Created by wangch on 2019/3/29
 */
public class SiteCount {

    public SiteCount(long site_code, Double dis) {
        this.site_code = site_code;
        this.dis = dis;
    }

    public SiteCount() {
    }

    private long site_code;

    private Double dis;

    public long getSite_code() {
        return site_code;
    }

    public void setSite_code(long site_code) {
        this.site_code = site_code;
    }

    public Double getDis() {
        return dis;
    }

    public void setDis(Double dis) {
        this.dis = dis;
    }
}
