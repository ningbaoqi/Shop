package com.shop.ningbaoqi.ningbaoqi_shop.icon;


import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class FontModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return Icons.values();
    }
}
