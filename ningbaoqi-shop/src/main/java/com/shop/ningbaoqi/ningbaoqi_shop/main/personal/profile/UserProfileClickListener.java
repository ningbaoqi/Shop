package com.shop.ningbaoqi.ningbaoqi_shop.main.personal.profile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.ui.date.DateDialogUtil;
import com.shop.ningbaoqi.ningbaoqi_core.util.callback.CallBackManager;
import com.shop.ningbaoqi.ningbaoqi_core.util.callback.CallbackType;
import com.shop.ningbaoqi.ningbaoqi_core.util.callback.IGlobalCallback;
import com.shop.ningbaoqi.ningbaoqi_shop.R;
import com.shop.ningbaoqi.ningbaoqi_shop.main.personal.list.ListBean;

public class UserProfileClickListener extends SimpleClickListener {
    private final NingbaoqiDelegate DELEGATE;
    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(NingbaoqiDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) adapter.getData().get(position);
        final int id = bean.getmId();
        switch (id) {
            case 1:
                CallBackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback() {
                    @Override
                    public void executeCallback(Object args) {
                        Log.d("nbq", "args" + args);
                    }
                });
                //开启照相机或选择图片
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final NingbaoqiDelegate nameDelegate = bean.getmDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;

        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
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
