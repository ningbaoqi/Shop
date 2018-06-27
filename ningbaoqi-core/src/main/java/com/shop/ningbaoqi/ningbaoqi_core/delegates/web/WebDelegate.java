package com.shop.ningbaoqi.ningbaoqi_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.shop.ningbaoqi.ningbaoqi_core.app.ConfigType;
import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * WEBVIEW在布局文件中创建的时候会出现内存泄露，在java文件中new会很大程度上避免
 */
public abstract class WebDelegate extends NingbaoqiDelegate implements IWebViewInitializer {
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private NingbaoqiDelegate mTopDelegate = null;

    public WebDelegate() {

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference = new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);//再次经过初始化的webview就变成了全新的webview
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Latte.getConfiguration(ConfigType.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(NingbaoqiWebInterface.create(this), name);
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("initializer must be initial");
            }
        }
    }

    public void setTopDelegate(NingbaoqiDelegate delegate) {
        mTopDelegate = delegate;
    }

    public NingbaoqiDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("webview is null");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("webview is null");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
