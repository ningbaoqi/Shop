package com.shop.ningbaoqi.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shop.ningbaoqi.ningbaoqi_core.activities.ProxyActivity;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.launcher.LauncherDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.launcher.LauncherScrollDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public NingbaoqiDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
