package cn.com.tianyudg.rxretrofitmvpdemo.basic.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Auther:  winds
 * Data:    2017/5/13
 * Desc:
 */

public class PreferenceUtils {

    private static final String DEFAULT_SP_NAME = "config";

    /**
     * 保存Preference信息
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putPreference(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        if (value instanceof String) {
            sp.edit().putString(key, (String) value).apply();
        } else if (value instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            sp.edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Long) {
            sp.edit().putLong(key, (Long) value).apply();
        }
    }

    /**
     * 获取Preference信息
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getPreference(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    /**
     * 获取Preference信息
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Boolean getPreference(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 获取Preference信息
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Integer getPreference(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * 获取Preference信息
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Long getPreference(Context context, String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    /**
     * 去除某一选项
     * @param context
     * @param key
     */
    public static void removePreference(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).apply();
    }

    /**
     * 删除全部
     * @param context
     */
    public static void clearPreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }


}
