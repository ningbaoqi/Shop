package com.shop.ningbaoqi.ningbaoqi_core.net;

import android.content.Context;

import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IError;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IFailure;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IRequest;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.ISuccess;
import com.shop.ningbaoqi.ningbaoqi_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {
    private String mUrl = null;
    private static final WeakHashMap<String, Object> mParams = RestCreater.getParams();
    private IRequest mRequest = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> mParams) {
        this.mParams.putAll(mParams);
        return this;
    }


    public final RestClientBuilder params(String key, Object value) {
        this.mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess isuccess) {
        this.mSuccess = isuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure ifailure) {
        this.mFailure = ifailure;
        return this;
    }

    public final RestClientBuilder error(IError ierror) {
        this.mError = ierror;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder dir(String mDownloadDir) {
        this.mDownloadDir = mDownloadDir;
        return this;
    }

    public final RestClientBuilder extension(String mExtension) {
        this.mExtension = mExtension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, mParams, mRequest, mSuccess, mFailure, mError, mBody, mLoaderStyle, mContext, mFile, mDownloadDir, mExtension, mName);
    }
}
