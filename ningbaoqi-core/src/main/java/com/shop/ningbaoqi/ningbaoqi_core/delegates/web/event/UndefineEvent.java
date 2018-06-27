package com.shop.ningbaoqi.ningbaoqi_core.delegates.web.event;

import com.shop.ningbaoqi.ningbaoqi_core.util.log.LogUtils;

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LogUtils.d("UndefineEvent", params);
        return null;
    }
}
