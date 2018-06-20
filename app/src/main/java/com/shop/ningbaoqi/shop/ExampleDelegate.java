package com.shop.ningbaoqi.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;

public class ExampleDelegate extends NingbaoqiDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
