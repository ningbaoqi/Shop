package com.shop.ningbaoqi.ningbaoqi_shop.main.personal.list;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shop.ningbaoqi.ningbaoqi_shop.R;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.util.List;

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {
    private static final RequestOptions OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().dontAnimate();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATER, R.layout.arrow_item_avater);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_value, item.getmValue());
                break;
            case ListItemType.ITEM_AVATER:
                Glide.with(mContext).load(item.getmImageUrl()).apply(OPTIONS).into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            default:
                break;
        }
    }
}
