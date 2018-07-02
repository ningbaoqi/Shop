package com.shop.ningbaoqi.ningbaoqi_shop.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.GetChars;
import android.view.View;

import com.shop.ningbaoqi.ningbaoqi_core.delegates.bottom.BottomItemDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.R;
import com.shop.ningbaoqi.ningbaoqi_shop.R2;
import com.shop.ningbaoqi.ningbaoqi_shop.main.personal.list.ListAdapter;
import com.shop.ningbaoqi.ningbaoqi_shop.main.personal.list.ListBean;
import com.shop.ningbaoqi.ningbaoqi_shop.main.personal.list.ListItemType;
import com.shop.ningbaoqi.ningbaoqi_shop.main.personal.order.OrderListDelegate;
import com.shop.ningbaoqi.ningbaoqi_shop.main.personal.profile.UserProfileDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalDelegate extends BottomItemDelegate {
    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    private void startOrderListByType() {
        OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder().setItemType(ListItemType.ITEM_NORMAL).setId(1).setText("收货地址").build();
        final ListBean system = new ListBean.Builder().setItemType(ListItemType.ITEM_NORMAL).setId(2).setText("系统设置").build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
    }
}
