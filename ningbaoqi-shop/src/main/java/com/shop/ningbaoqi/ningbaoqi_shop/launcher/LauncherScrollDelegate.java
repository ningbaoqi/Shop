package com.shop.ningbaoqi.ningbaoqi_shop.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.ui.launcher.LauncherHolderCreator;
import com.shop.ningbaoqi.ningbaoqi_core.ui.launcher.ScrollLauncherTag;
import com.shop.ningbaoqi.ningbaoqi_core.util.storage.NingbaoqiPreference;
import com.shop.ningbaoqi.ningbaoqi_shop.R;

import java.util.ArrayList;

public class LauncherScrollDelegate extends NingbaoqiDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> convenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private void initBanner() {
        INTEGERS.add(R.drawable.launcher_01);
        INTEGERS.add(R.drawable.launcher_02);
        INTEGERS.add(R.drawable.launcher_03);
        INTEGERS.add(R.drawable.launcher_04);
        INTEGERS.add(R.drawable.launcher_05);
        convenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);

    }


    @Override
    public Object setLayout() {
        convenientBanner = new ConvenientBanner<>(getContext());
        return convenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1) {//如果点击的是最后一个
            NingbaoqiPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登陆
        }
    }
}
