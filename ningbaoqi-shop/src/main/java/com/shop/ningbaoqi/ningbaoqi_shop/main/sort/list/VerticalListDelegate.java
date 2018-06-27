package com.shop.ningbaoqi.ningbaoqi_shop.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.net.RestClient;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.ISuccess;
import com.shop.ningbaoqi.ningbaoqi_core.ui.recycler.MultipleItemEntity;
import com.shop.ningbaoqi.ningbaoqi_shop.R;
import com.shop.ningbaoqi.ningbaoqi_shop.R2;
import com.shop.ningbaoqi.ningbaoqi_shop.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

public class VerticalListDelegate extends NingbaoqiDelegate {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽掉动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    /**
     * 进行数据处理
     *
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().url("http://192.168.1.32:8080/RestServer/RestServer/data/sort_list_data.json").loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data = new VerticalListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                }).build().get();
    }
}
