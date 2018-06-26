package com.shop.ningbaoqi.ningbaoqi_shop.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.bottom.BottomItemDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.ui.recycler.BaseDecoration;
import com.shop.ningbaoqi.ningbaoqi_core.ui.refresh.RefreshHandler;
import com.shop.ningbaoqi.ningbaoqi_shop.R;
import com.shop.ningbaoqi.ningbaoqi_shop.R2;
import com.shop.ningbaoqi.ningbaoqi_shop.main.ShopBottomDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.main.sort.SortDelegate;

import java.util.ArrayList;

import butterknife.BindView;

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;
    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mSwipeRefreshLayout, mRecyclerView, new IndexDataConverter());
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 8));

        final ShopBottomDelegate shopBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(shopBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("http://192.168.1.32:8080/RestServer/RestServer/data/index_data.json");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
