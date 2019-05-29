package com.auxiliarybus.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangchaohui on 2018/1/18.
 *
 * @Description ����վ����
 */
public class BusSiteLocation {

    public BusSiteLocation(Integer id, long siteCode, String siteName, String longitude, String latitude) {
        /**����վ̨��ϵͳ���*/
        this.id = id;
        /**����վ̨ԭʼ��ţ���Դ������˾��*/
        this.siteCode = siteCode;
        /**����վ̨��*/
        this.siteName = siteName;
        /**����վ̨����*/
        this.longitude = longitude;
        /**����վ̨ά��*/
        this.latitude = latitude;
    }

    public BusSiteLocation() {
    }

    private Integer id;
    /**
     * Դ����id  ��·���
     */
    private long siteCode;
    /**
     * վ����
     */
    private String siteName;
    /**
     * ����
     */
    private String longitude;
    /**
     * ά��
     */
    private String latitude;

    /**
     * վ�����������ͳ��
     */
    private int surveyCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(long siteCode) {
        this.siteCode = siteCode;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getSurveyCount() {
        return surveyCount;
    }

    public void setSurveyCount(int surveyCount) {
        this.surveyCount = surveyCount;
    }

    @Override
    public String toString() {
        return "BusSiteLocation{" +
                "id=" + id +
                ", siteCode=" + siteCode +
                ", siteName='" + siteName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", surveyCount=" + surveyCount +
                '}';
    }
}
