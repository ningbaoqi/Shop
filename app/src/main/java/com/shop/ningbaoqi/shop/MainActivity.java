package com.shop.ningbaoqi.shop;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.Toast;

import com.shop.ningbaoqi.ningbaoqi_core.activities.ProxyActivity;
import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.ui.launcher.ILauncherListener;
import com.shop.ningbaoqi.ningbaoqi_core.ui.launcher.OnLauncherFinishTag;
import com.shop.ningbaoqi.ningbaoqi_shop.main.ShopBottomDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.sign.ISignListener;
import com.shop.ningbaoqi.ningbaoqi_shop.sign.SignInDelegate;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public NingbaoqiDelegate setRootDelegate() {
        return new ShopBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登陆了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new ShopBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登陆", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
