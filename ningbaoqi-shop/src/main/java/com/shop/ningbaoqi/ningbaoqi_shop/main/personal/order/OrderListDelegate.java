package com.shop.ningbaoqi.ningbaoqi_shop.main.personal.order;

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
import com.shop.ningbaoqi.ningbaoqi_shop.main.personal.PersonalDelegate;

import java.util.List;

import butterknife.BindView;

public class OrderListDelegate extends NingbaoqiDelegate {
    private String mType = null;
    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().loader(getContext()).url("http://192.168.1.32:8080/RestServer/RestServer/data/order_list.json")
                //.params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data = new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                }).build().get();
    }
}
