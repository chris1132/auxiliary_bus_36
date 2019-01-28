package com.auxiliarybus.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangchaohui on 2018/1/18.
 * @Description 公交站点类
 */
public class BusSiteLocation extends BaseEntity {
    private Integer id;
    /**
     * 源数据id
     * */
    private int code;
    /**
     * 站点名
     * */
    private String siteName;
    /**
     * 经度
     * */
    private String longitude;
    /**
     * 维度
     * */
    private Integer latitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }
}
