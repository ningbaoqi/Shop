package com.shop.ningbaoqi.ningbaoqi_shop.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.ningbaoqi.ningbaoqi_core.app.ConfigType;
import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.net.RestClient;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.ISuccess;
import com.shop.ningbaoqi.ningbaoqi_core.ui.loader.NingbaoqiLoader;
import com.shop.ningbaoqi.ningbaoqi_core.util.log.LogUtils;
import com.shop.ningbaoqi.ningbaoqi_core.wechat.NingbaoqiWeChat;
import com.shop.ningbaoqi.ningbaoqi_shop.R;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

public class FastPay implements View.OnClickListener {
    //设置支付回调监听
    private IAlPayResultListener alPayResultListener = null;
    private Activity mActivity = null;
    private AlertDialog mDialog = null;
    private int mOrderID = -1;

    private FastPay(NingbaoqiDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(NingbaoqiDelegate delegate) {
        return new FastPay(delegate);
    }

    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            window.findViewById(R.id.btn_dialog_pay_alipay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    public FastPay setPayResultListener(IAlPayResultListener listener) {
        this.alPayResultListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId) {
        this.mOrderID = orderId;
        return this;
    }

    /**
     * 不一样的服务器请求地址也不一样
     *
     * @param orderId
     */
    private final void alPay(int orderId) {
        final String stringUrl = "你的服务端支付地址" + orderId;
        //获取签名字符串
        RestClient.builder().url(stringUrl).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final String paySign = JSON.parseObject(response).getString("result");
                //必须异步调用客户端支付接口
                final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, alPayResultListener);
                payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
            }
        }).build().post();
    }

    private final void wechatPay(int mOrderID) {
        NingbaoqiLoader.stopLoading();
        final String weChatPrePayUrl = "你的服务端微信预支付地址" + mOrderID;
        LogUtils.d("nbq", weChatPrePayUrl);
        final IWXAPI iwxapi = NingbaoqiWeChat.getInstance().getWXAPI();
        final String appId = Latte.getConfiguration(ConfigType.WE_CHAT_APP_ID);
        iwxapi.registerApp(appId);
        RestClient.builder().url(weChatPrePayUrl).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final JSONObject result = JSON.parseObject(response).getJSONObject("result");
                final String prepayId = result.getString("prepayid");
                final String partnerId = result.getString("partnerid");
                final String packageValue = result.getString("package");
                final String timestamp = result.getString("timestamp");
                final String nonceStr = result.getString("noncestr");
                final String paySign = result.getString("sign");

                final PayReq payReq = new PayReq();
                payReq.appId = appId;
                payReq.prepayId = prepayId;
                payReq.partnerId = partnerId;
                payReq.packageValue = packageValue;
                payReq.timeStamp = timestamp;
                payReq.nonceStr = nonceStr;
                payReq.sign = paySign;
                iwxapi.sendReq(payReq);
            }
        }).build().post();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alipay) {
            alPay(mOrderID);
        } else if (id == R.id.btn_dialog_pay_wechat) {
            wechatPay(mOrderID);
        } else if (id == R.id.btn_dialog_pay_cancel) {

        }
        mDialog.cancel();
    }
}