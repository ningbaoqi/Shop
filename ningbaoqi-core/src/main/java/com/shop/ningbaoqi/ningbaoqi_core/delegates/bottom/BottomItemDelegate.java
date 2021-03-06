package com.shop.ningbaoqi.ningbaoqi_core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.shop.ningbaoqi.ningbaoqi_core.R;
import com.shop.ningbaoqi.ningbaoqi_core.delegates.NingbaoqiDelegate;

public abstract class BottomItemDelegate extends NingbaoqiDelegate {
    private long TOUCH_TIME = 0;
    private static final long WAIT_TIME = 2000L;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_LONG).show();
        }
        return true;
    }

    //    /**
//     * 为了使OnKeyListener点击事件生效
//     */
//    @Override
//    public void onResume() {
//        super.onResume();
//        final View rootView = getView();
//        if (rootView != null) {
//            rootView.setFocusableInTouchMode(true);
//            rootView.requestFocus();
//            rootView.setOnKeyListener(this);
//        }
//    }
//
//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
//                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_LONG).show();
//                mExitTime = System.currentTimeMillis();
//            } else {
//                _mActivity.finish();
//                if (mExitTime != 0) {
//                    mExitTime = 0;
//                }
//            }
//            return true;
//        }
//        return false;
//    }
}
