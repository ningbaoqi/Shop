package com.shop.ningbaoqi.ningbaoqi_core.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.web.event.Event;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.web.event.EventManager;
import com.shop.ningbaoqi.ningbaoqi_core.util.log.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import retrofit2.http.PUT;

/**
 * 配置文件的存储和获取
 */
public class Configurator {
    private static final HashMap<Object, Object> CONFIGS = new HashMap<>();
    //字体库，封装
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }


    private Configurator() {
        CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);
    }

    /**
     * 单例模式，优雅
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final void configure() {
        initIcons();
        CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
        Utils.init(Latte.getApplicationContext());
    }


    final HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }


    private void checkConfigaration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigType.CONFIG_READY.name());
        LogUtils.d("nbq", isReady + "");
        if (!isReady) {
            throw new RuntimeException("Configuartion is not ready , call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfigaration();
        return (T) CONFIGS.get(key.name());
    }

    /**
     * 初始化字体图标库
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        CONFIGS.put(ConfigType.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        CONFIGS.put(ConfigType.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }
}
