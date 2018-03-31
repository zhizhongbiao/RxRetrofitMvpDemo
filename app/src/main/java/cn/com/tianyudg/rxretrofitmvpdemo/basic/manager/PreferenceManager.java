package cn.com.tianyudg.rxretrofitmvpdemo.basic.manager;


import cn.com.tianyudg.rxretrofitmvpdemo.basic.MainApplication;

/**
 * Author : WaterFlower.
 * Created on 2017/8/11.
 * Desc :
 */
public class PreferenceManager {
    public static final String USER_MOBILE          = "USER_MOBILE";
    public static final String SERVICE_PHONE        = "SERVICE_PHONE";
    public static final String USER_NICK_NAME       = "USER_NICK_NAME";
    public static final String MAIN_PREFERENCE      = "INFO_PREFERENCE";
    public static final String LAST_ACTIVE_TIME     = "LAST_ACTIVE_TIME";
    public static final long   SESSION_VALID_PERIOD = 2592000000L;
    public static final String USER_NAME            = "USER_NAME";
    public static final String USER_PASSWORD        = "USER_PASSWORD";


    public static boolean isSessionValid() {
        long elapseTime = System.currentTimeMillis() - MainApplication.getMainPreferences().getLong(LAST_ACTIVE_TIME, 0);
        return elapseTime > 0 && elapseTime < SESSION_VALID_PERIOD;
    }

    public static String getString(String key, String defaultValue) {
        return MainApplication.getMainPreferences().getString(key, defaultValue);
    }

    public static void putString(String key, String value) {
        MainApplication.getMainPreferences().edit()
                .putString(key, value).apply();
    }

    public static int getInt(String key, int defaultValue) {
        return MainApplication.getMainPreferences().getInt(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        MainApplication.getMainPreferences().edit()
                .putInt(key, value).apply();
    }


    public static float getFloat(String key, float defaultValue) {
        return MainApplication.getMainPreferences().getFloat(key, defaultValue);
    }

    public static void putFloat(String key, float value) {
        MainApplication.getMainPreferences().edit()
                .putFloat(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return MainApplication.getMainPreferences().getBoolean(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        MainApplication.getMainPreferences().edit()
                .putBoolean(key, value).apply();
    }
}
