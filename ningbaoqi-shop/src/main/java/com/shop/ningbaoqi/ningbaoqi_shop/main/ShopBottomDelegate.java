package com.shop.ningbaoqi.ningbaoqi_shop.main;

import android.graphics.Color;

import com.shop.ningbaoqi.ningbaoqi_core.delegates.bottom.BaseBottomDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.bottom.BottomItemDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.bottom.BottomTabBean;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.bottom.ItemBuilder;
import com.shop.ningbaoqi.ningbaoqi_shop.main.cart.ShopCartDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.main.discover.DiscoverDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.main.index.IndexDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.main.personal.PersonalDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class ShopBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "设置"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
