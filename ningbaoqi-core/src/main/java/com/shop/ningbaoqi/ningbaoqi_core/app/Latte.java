package com.shop.ningbaoqi.ningbaoqi_core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *
 */
public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance().getConfigs().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
