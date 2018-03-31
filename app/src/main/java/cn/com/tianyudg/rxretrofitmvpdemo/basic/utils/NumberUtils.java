package cn.com.tianyudg.rxretrofitmvpdemo.basic.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * @author 刘琛慧
 *         date 2016/7/4.
 */
public class NumberUtils {

    public static int parseInt(Object number, int defaultValue) {

        if (number == null) {
            return defaultValue;
        }

        if (number instanceof Integer) {
            return (Integer) number;
        }

        String numStr = number.toString();
        if (TextUtils.isEmpty(numStr) || !TextUtils.isDigitsOnly(numStr)) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(numStr);
        } catch (Exception ex) {
            try {
                return Double.valueOf(numStr).intValue();
            } catch (Exception e) {
                return defaultValue;
            }
        }
    }

    public static double parseDouble(Object number, double defaultValue) {

        if (number == null) {
            return defaultValue;
        }


        if (number instanceof Double) {
            return (Double) number;
        }

        String numStr = number.toString();
        if (TextUtils.isEmpty(numStr) ) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(numStr);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public static Long parseLong(Object number, long defaultValue) {

        if (number == null) {
            return defaultValue;
        }


        if (number instanceof Long) {
            return (Long) number;
        }

        String numStr = number.toString();
        if (TextUtils.isEmpty(numStr) ) {
            return defaultValue;
        }

        try {
            return Long.parseLong(numStr);
        } catch (Exception ex) {
            return defaultValue;
        }
    }


    public static boolean parseBool(Object value, boolean defaultValue) {

        if (value == null) {
            return defaultValue;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }


        String valStr = value.toString();
        if (TextUtils.isEmpty(valStr)) {
            return defaultValue;
        }

        try {
            return Boolean.parseBoolean(valStr);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    /**
     * 高精度加法
     *
     * @param valueA
     * @param valueB
     * @return
     */
    public static double add(double valueA, double valueB) {
        BigDecimal bigDecimal = BigDecimal.valueOf(valueA);
        bigDecimal = bigDecimal.add(BigDecimal.valueOf(valueB));
        return bigDecimal.doubleValue();
    }

    /**
     * 高精度减法
     *
     * @param valueA
     * @param valueB
     * @return
     */
    public static double sub(double valueA, double valueB) {
        BigDecimal bigDecimal = BigDecimal.valueOf(valueA);
        bigDecimal = bigDecimal.subtract(BigDecimal.valueOf(valueB));
        return bigDecimal.doubleValue();
    }

    /**
     * 高精度乘法
     *
     * @param valueA
     * @param valueB
     * @return
     */
    public static double multiply(double valueA, double valueB) {
        BigDecimal bigDecimal = BigDecimal.valueOf(valueA);
        bigDecimal = bigDecimal.multiply(BigDecimal.valueOf(valueB));

        return bigDecimal.doubleValue();
    }


    /**
     * 高精度除法
     *
     * @param valueA
     * @param valueB
     * @return
     */
    public static double div(double valueA, double valueB) {
        BigDecimal bigDecimal = BigDecimal.valueOf(valueA);
        bigDecimal = bigDecimal.divide(BigDecimal.valueOf(valueB));

        return bigDecimal.doubleValue();
    }
}
