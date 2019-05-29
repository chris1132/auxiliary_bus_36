package com.auxiliarybus.entity;

/**
 * Created by wangch on 2019/3/27
 */
public class SurveyData extends BaseEntity {

    private int id;
    private int grade;
    private int nightBus;
    private String School;
    private String HomeLocation;
    private String Location;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getHomeLocation() {
        return HomeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        HomeLocation = homeLocation;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
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
}
