package com.shop.ningbaoqi.ningbaoqi_core.ui.banner;


import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.shop.ningbaoqi.ningbaoqi_core.R;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;

import java.util.ArrayList;

public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner, ArrayList<String> banners, OnItemClickListener clickListener) {
        convenientBanner.setPages(new HolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }
}
