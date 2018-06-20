package com.shop.ningbaoqi.ningbaoqi_core.app;

import java.util.WeakHashMap;

/**
 * 配置文件的存储和获取
 */
public class Configurator {
    //当该map中的键值对不使用的时候，会及时的回收内存，可以最大限度的防止内存爆满
    private static final WeakHashMap<String, Object> CONFIGS = new WeakHashMap<>();

    private Configurator() {
        CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
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

    /**
     * 配置完成了
     */
    public final void configure() {
        CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }


    final WeakHashMap<String, Object> getConfigs() {
        return CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }


    private void checkConfigaration() {
        final boolean isReady = (boolean) CONFIGS.get(CONFIGS.get(ConfigType.CONFIG_READY.name()));
        if (!isReady) {
            throw new RuntimeException("Configuartion is not ready , call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfigaration();
        return (T) CONFIGS.get(key.name());
    }
}
