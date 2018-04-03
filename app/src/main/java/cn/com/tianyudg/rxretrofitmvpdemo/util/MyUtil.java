package cn.com.tianyudg.rxretrofitmvpdemo.util;

/**
 * Author : WaterFlower.
 * Created on 2018/3/31.
 * Desc :
 */

public class MyUtil {

    public static <T> T checkNotNull(T reference,String msg) {
        if(reference == null) {
            throw new NullPointerException(msg);
        } else {
            return reference;
        }
    }
}
