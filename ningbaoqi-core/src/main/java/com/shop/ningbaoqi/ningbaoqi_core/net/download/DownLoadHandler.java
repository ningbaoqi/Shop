package com.shop.ningbaoqi.ningbaoqi_core.net.download;

import android.os.AsyncTask;

import com.shop.ningbaoqi.ningbaoqi_core.net.RestCreater;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IError;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IFailure;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IRequest;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownLoadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreater.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAULURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownLoadHandler(String url, IRequest request, ISuccess success, IFailure failure, IError error, String downloadDir, String extension, String name) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAULURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreater.getRestService().downLoad(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    final SavaFileTask task = new SavaFileTask(REQUEST, SUCCESS);
//                task.execute();//是以队列的形式一个一个执行
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, response.body(), NAME);//以线程池形式执行
                    /**
                     * 这里一定要注意判断，否则文件下载不全
                     */
                    if (task.isCancelled()) {
                        if (REQUEST != null) {
                            REQUEST.onRequestEnd();
                        }
                    }
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAULURE != null) {
                    FAULURE.onFailure();
                }
            }
        });
    }
}
