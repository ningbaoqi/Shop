package com.shop.ningbaoqi.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.net.RestClient;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IError;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.IFailure;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.ISuccess;

public class ExampleDelegate extends NingbaoqiDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://192.168.1.32:8080/RestServer/RestServer/api/index.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {

            }
        }).build().get();
    }
}
