package com.shop.ningbaoqi.ningbaoqi_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;

public class DimenUtil {
    /**
     * 获取屏幕的宽
     *
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高
     *
     * @return
     */
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
