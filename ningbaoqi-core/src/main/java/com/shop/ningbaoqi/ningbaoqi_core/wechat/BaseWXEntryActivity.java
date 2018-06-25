package com.shop.ningbaoqi.ningbaoqi_core.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.ningbaoqi.ningbaoqi_core.net.RestClient;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IError;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IFailure;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.ISuccess;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    /**
     * 用户登录成功后回调
     */
    protected abstract void onSignInSuccess(String userInfo);

    /**
     * 第三方应用发送请求到微信后的回调
     *
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=").append(NingbaoqiWeChat.APP_ID).append("&secret=").append(NingbaoqiWeChat.APP_SECRET)
                .append("&code=").append(code).append("&grant_type=authorization_code");
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {
        RestClient.builder().url(authUrl).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final JSONObject autoObj = JSON.parseObject(response);
                final String accessToken = autoObj.getString("access_token");
                final String openID = autoObj.getString("openid");

                final StringBuilder userInfoUrl = new StringBuilder();
                userInfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                        .append(accessToken)
                        .append("&openid=")
                        .append(openID)
                        .append("&lang=")
                        .append("zh_CN");
                getUserInfo(userInfoUrl.toString());
            }
        }).build().get();
    }


    private void getUserInfo(String userInfoUrl) {
        RestClient.builder().url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                }).build().get();
    }


    /**
     * 微信发送请求到第三方应用后的回调
     *
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }
}
