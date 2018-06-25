package com.shop.ningbaoqi.ningbaoqi_core.wechat.template;

import com.shop.ningbaoqi.ningbaoqi_core.activities.ProxyActivity;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.wechat.BaseWXEntryActivity;
import com.shop.ningbaoqi.ningbaoqi_core.wechat.NingbaoqiWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {
    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);//不需要有任何的动画效果
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        NingbaoqiWeChat.getInstance().getCallback().onSignInSuccess(userInfo);
    }
}
