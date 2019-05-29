package com.auxiliarybus.entity;

/**
 * Created by wangch on 2019/4/24
 */
public class PointLocation {

    long id;

    String pointName;

    String longitude;

    String latitude;


    public PointLocation(long id, String pointName, String longitude, String latitude) {
        this.id = id;
        this.pointName = pointName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
