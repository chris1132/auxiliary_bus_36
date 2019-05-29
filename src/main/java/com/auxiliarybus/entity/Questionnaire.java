package com.auxiliarybus.entity;

/**
 * Created by wangch on 2019/3/26
 */
public class Questionnaire extends BaseEntity {
    public Questionnaire() {
    }

    public Questionnaire(int id, long affiliatedSiteCode) {
        this.id = id;
        this.affiliatedSiteCode = affiliatedSiteCode;
    }

    public Questionnaire(int id, String name, String longitude, String latitude, long affiliatedSiteCode, int grade,int nightBus) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.affiliatedSiteCode = affiliatedSiteCode;
        this.grade = grade;
        this.nightBus = nightBus;
    }

    private int id;

    private String name;

    private String longitude;

    private String latitude;

    private long affiliatedSiteCode;

    private int grade;

    private int nightBus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getAffiliatedSiteCode() {
        return affiliatedSiteCode;
    }

    public void setAffiliatedSiteCode(long affiliatedSiteCode) {
        this.affiliatedSiteCode = affiliatedSiteCode;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getNightBus() {
        return nightBus;
    }

    public void setNightBus(int nightBus) {
        this.nightBus = nightBus;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", affiliatedSiteCode=" + affiliatedSiteCode +
                ", grade=" + grade +
                ", nightBus=" + nightBus +
                '}';
    }
}
