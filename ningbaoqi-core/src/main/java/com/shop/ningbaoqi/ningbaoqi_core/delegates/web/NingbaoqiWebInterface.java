package com.shop.ningbaoqi.ningbaoqi_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.web.event.Event;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.web.event.EventManager;

/**
 * 用来与原生进行交互的
 */
final class NingbaoqiWebInterface {
    private final WebDelegate DELEGATE;

    public NingbaoqiWebInterface(WebDelegate webDelegate) {
        this.DELEGATE = webDelegate;
    }

    static NingbaoqiWebInterface create(WebDelegate webDelegate) {
        return new NingbaoqiWebInterface(webDelegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String param) {
        final String action = JSON.parseObject(param).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(param);
        }
        return null;
    }
}
