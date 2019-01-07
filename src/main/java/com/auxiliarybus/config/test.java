package com.auxiliarybus.config;

/**
 * Created by wangchaohui on 2019/1/3
 */
public class test {

    public static void main(String[] args) {
        int a1 = 127;
        Integer au = 127;
        Integer s = 127;
        System.out.println(au==s);
        System.out.println(a1==s);

        int a2 = 128;
        Integer s1 = 128;
        Integer s2 = 128;
        System.out.println(s1==s2);
        System.out.println(a2==s2);

    }
}
