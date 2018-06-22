package com.shop.ningbaoqi.shop;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;
import com.shop.ningbaoqi.ningbaoqi_core.net.interceptors.DebugInterceptor;
import com.shop.ningbaoqi.ningbaoqi_shop.icon.FontModule;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
