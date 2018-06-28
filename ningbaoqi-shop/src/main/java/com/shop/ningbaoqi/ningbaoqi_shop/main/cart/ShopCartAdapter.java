package com.shop.ningbaoqi.ningbaoqi_shop.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.shop.ningbaoqi.ningbaoqi_core.app.Latte;
import com.shop.ningbaoqi.ningbaoqi_core.net.RestClient;
import com.shop.ningbaoqi.ningbaoqi_core.net.callback.ISuccess;
import com.shop.ningbaoqi.ningbaoqi_core.ui.recycler.MultipleFields;
import com.shop.ningbaoqi.ningbaoqi_core.ui.recycler.MultipleItemEntity;
import com.shop.ningbaoqi.ningbaoqi_core.ui.recycler.MultipleRecyclerAdapter;
import com.shop.ningbaoqi.ningbaoqi_core.ui.recycler.MultipleViewHolder;
import com.shop.ningbaoqi.ningbaoqi_shop.R;

import java.util.List;

public class ShopCartAdapter extends MultipleRecyclerAdapter {
    private boolean mIsSelectedAll = false;
    private static final RequestOptions OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().dontAnimate();


    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice += total;
        }
        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);

                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext).load(thumb).apply(OPTIONS).into(imgThumb);

                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);


                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelectd = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelectd) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });

                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int count = entity.getField(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder().url("shop_cart_count").loader(mContext).params("count", count).success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    int countMum = Integer.parseInt(tvCount.getText().toString());
                                    countMum--;
                                    tvCount.setText(String.valueOf(countMum));
                                    if (mICartItemListener != null) {
                                        mTotalPrice = mTotalPrice - price;
                                        final double itemTotal = countMum * price;
                                        mICartItemListener.onItemClick(itemTotal);
                                    }
                                }
                            }).build().post();
                        }
                    }
                });

                //添加增加事件
                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        RestClient.builder().url("shop_cart_count").loader(mContext).params("count", count).success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                int countMum = Integer.parseInt(tvCount.getText().toString());
                                countMum++;
                                tvCount.setText(String.valueOf(countMum));
                                tvCount.setText(String.valueOf(countMum));
                                if (mICartItemListener != null) {
                                    mTotalPrice = mTotalPrice + price;
                                    final double itemTotal = countMum * price;
                                    mICartItemListener.onItemClick(itemTotal);
                                }
                            }
                        }).build().post();
                    }
                });
                break;
        }
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }


    private double mTotalPrice = 0.00;
    private ICartItemListener mICartItemListener = null;

    public void setmICartItemListener(ICartItemListener listener) {
        this.mICartItemListener = listener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }
}
