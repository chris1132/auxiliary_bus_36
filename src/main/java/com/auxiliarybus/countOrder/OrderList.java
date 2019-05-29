package com.auxiliarybus.countOrder;

import java.util.Arrays;

/**
 * Created by wangch on 2019/5/28
 */
public class OrderList {

    private int total;
    private String[] orders;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String[] getOrders() {
        return orders;
    }

    public void setOrders(String[] orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "total=" + total +
                ", orders=" + Arrays.toString(orders) +
                '}';
    }
}
