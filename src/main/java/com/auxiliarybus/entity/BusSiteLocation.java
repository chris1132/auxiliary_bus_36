package com.auxiliarybus.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangchaohui on 2018/1/18.
 * @Description ����վ����
 */
public class BusSiteLocation extends BaseEntity {
    private Integer id;
    /**
     * Դ����id
     * */
    private int code;
    /**
     * վ����
     * */
    private String siteName;
    /**
     * ����
     * */
    private String longitude;
    /**
     * ά��
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
