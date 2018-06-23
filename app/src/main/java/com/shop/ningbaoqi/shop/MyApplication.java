package com.shop.ningbaoqi.shop;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;
import com.shop.ningbaoqi.ningbaoqi_core.net.interceptors.DebugInterceptor;
import com.shop.ningbaoqi.ningbaoqi_shop.database.DatabaseManager;
import com.shop.ningbaoqi.ningbaoqi_shop.icon.FontModule;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("asd", R.raw.test))
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }


    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build());
    }
}
