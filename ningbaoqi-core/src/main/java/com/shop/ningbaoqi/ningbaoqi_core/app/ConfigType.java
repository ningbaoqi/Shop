package com.shop.ningbaoqi.ningbaoqi_core.app;

/**
 * 枚举类，在整个应用程序中是唯一的单例，并且只能被初始化一次
 */
public enum ConfigType {
    API_HOST,   //网络请求的域名
    APPLICATION_CONTEXT,    //全局的上下文
    CONFIG_READY,    //初始化是否完成
    ICON,
    INTERCEPTOR,
    WE_CHAT_APP_ID,
    WE_CHAT_APP_SECRET,
    ACTIVITY,
    HANDLER,
    JAVASCRIPT_INTERFACE
}
