package com.shop.ningbaoqi.ningbaoqi_core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.IPageLoadListener;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.web.WebDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.web.route.Router;
import com.shop.ningbaoqi.ningbaoqi_core.ui.loader.NingbaoqiLoader;
import com.shop.ningbaoqi.ningbaoqi_core.util.log.LogUtils;

import retrofit2.http.DELETE;

public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler mHandler = Latte.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     * 如果返回false则由webview来处理事件
     *
     * @param view
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LogUtils.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        NingbaoqiLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NingbaoqiLoader.stopLoading();
            }
        }, 1000);
    }
}
