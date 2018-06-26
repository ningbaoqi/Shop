package com.shop.ningbaoqi.ningbaoqi_shop.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.detail.GoodsDetailDelegate;

public class IndexItemClickListener extends SimpleClickListener {
    private final NingbaoqiDelegate DELEGATE;

    private IndexItemClickListener(NingbaoqiDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(NingbaoqiDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create();
        DELEGATE.start(detailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
