package com.shop.ningbaoqi.ningbaoqi_core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;

public final class NingbaoqiPreference {
    /**
     * 提示：
     * activity.getPreferences()生成 Activity名.xml ，用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences生成 包名_preferences.xml
     * Context.getSharedPreferences(String name , int mode) 生成name.xml
     */
    private static final SharedPreferences PREFERENCES = PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreferences() {
        return PREFERENCES;
    }

    public static void setAppProfile(String val) {
        getAppPreferences().edit().putString(APP_PREFERENCES_KEY, val).apply();
    }

    public static String getAppProfile() {
        return getAppPreferences().getString(APP_PREFERENCES_KEY, null);
    }

    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreferences().edit().remove(APP_PREFERENCES_KEY).apply();
    }

    public static void clearAppPreferences() {
        getAppPreferences().edit().clear().apply();
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreferences().edit().putBoolean(key, flag).apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreferences().getBoolean(key, false);
    }
}
