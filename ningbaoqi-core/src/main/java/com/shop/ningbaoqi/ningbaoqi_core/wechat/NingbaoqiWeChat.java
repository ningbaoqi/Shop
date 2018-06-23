package com.shop.ningbaoqi.ningbaoqi_core.wechat;

import android.app.Activity;

import com.shop.ningbaoqi.ningbaoqi_core.app.ConfigType;
import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class NingbaoqiWeChat {
    static final String APP_ID = Latte.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;

    private NingbaoqiWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }


    private static final class Holder {
        private static final NingbaoqiWeChat INSTANCE = new NingbaoqiWeChat();
    }

    public static NingbaoqiWeChat getInstance() {
        return Holder.INSTANCE;
    }
}
