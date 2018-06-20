package com.shop.ningbaoqi.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shop.ningbaoqi.ningbaoqi_core.activities.ProxyActivity;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public NingbaoqiDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
