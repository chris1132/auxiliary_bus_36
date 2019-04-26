package com.auxiliarybus.prim;

import java.math.BigDecimal;

/**
 * Created by wangch on 2019/4/25
 */
public class DistanceComputerUtil {

    private static final double EARTH_RADIUS = 637_1393.00D;
    private static final double RADIAN = Math.PI / 180.00D;
    private static final double HALF = 0.5D;

    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double x, y, a, b, distance;
        lat1 *= RADIAN;
        lat2 *= RADIAN;
        x = lat1 - lat2;
        y = lon1 - lon2;
        y *= RADIAN;
        a = Math.sin(x * HALF);
        b = Math.sin(y * HALF);
        distance = EARTH_RADIUS * Math.asin(Math.sqrt(a * a + Math.cos(lat1) * Math.cos(lat2) * b * b)) / HALF;
        return new BigDecimal(distance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    }
}
