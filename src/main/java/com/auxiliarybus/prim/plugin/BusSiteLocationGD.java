package com.auxiliarybus.prim.plugin;

/**
 * Created by wangchaohui on 2018/1/18.
 *
 * @Description 公交站点类
 */
public class BusSiteLocationGD {

    public BusSiteLocationGD(Integer id, long siteCode, String siteName, String longitude, String latitude, int surveyCount) {
        /**公交站台本系统编号*/
        this.id = id;
        /**公交站台原始编号（来源公交公司）*/
        this.siteCode = siteCode;
        /**公交站台名*/
        this.siteName = siteName;
        /**公交站台经度*/
        this.longitude = longitude;
        /**公交站台维度*/
        this.latitude = latitude;
        /** 站点拟合需求数 */
        this.surveyCount = surveyCount;
    }

    public BusSiteLocationGD() {
    }

    private Integer id;
    /**
     * 源数据id  线路编号
     */
    private long siteCode;
    /**
     * 站点名
     */
    private String siteName;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 维度
     */
    private String latitude;

    /**
     * 站点拟合需求数（各站点附近300米范围内的需求点拟合到该站点，若一个需求点能拟合到多个站点，取最近一个站点）
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
