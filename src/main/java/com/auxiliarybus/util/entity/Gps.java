package com.auxiliarybus.util.entity;

/**
 * Created by wangch on 2019/3/29
 */
public class Gps {
    public double wgLon;
    public double wgLat;


    public Gps(double wgLon, double wgLat) {
        this.wgLon = wgLon;
        this.wgLat = wgLat;
    }

    public double getWgLat() {
        return wgLat;
    }

    public void setWgLat(double wgLat) {
        this.wgLat = wgLat;
    }

    public double getWgLon() {
        return wgLon;
    }

    public void setWgLon(double wgLon) {
        this.wgLon = wgLon;
    }
}
