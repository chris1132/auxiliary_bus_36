package com.auxiliarybus.countOrder;

/**
 * Created by wangch on 2019/5/28
 */
public class Order {
    String orderNo;
    String scheduledName;
    String startStopName;
    String endStopName;
    int payAmount;
    String startTime;
    int peopleocunt;


    public Order(String orderNo, String scheduledName, String startStopName, String endStopName, int payAmount, String startTime) {
        this.orderNo = orderNo;
        this.scheduledName = scheduledName;
        this.startStopName = startStopName;
        this.endStopName = endStopName;
        this.payAmount = payAmount;
        this.startTime = startTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getScheduledName() {
        return scheduledName;
    }

    public void setScheduledName(String scheduledName) {
        this.scheduledName = scheduledName;
    }

    public String getStartStopName() {
        return startStopName;
    }

    public void setStartStopName(String startStopName) {
        this.startStopName = startStopName;
    }

    public String getEndStopName() {
        return endStopName;
    }

    public void setEndStopName(String endStopName) {
        this.endStopName = endStopName;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public int getPeopleocunt() {
        return peopleocunt;
    }

    public void setPeopleocunt(int peopleocunt) {
        this.peopleocunt = peopleocunt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "scheduledName='" + scheduledName + '\'' +
                ", startStopName='" + startStopName + '\'' +
                ", endStopName='" + endStopName + '\'' +
                ", payAmount=" + payAmount +
                ", startTime='" + startTime + '\'' +
                ", peopleocunt=" + peopleocunt +
                '}';
    }
}
