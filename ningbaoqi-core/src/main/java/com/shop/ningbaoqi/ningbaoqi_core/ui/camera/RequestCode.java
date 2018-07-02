package com.shop.ningbaoqi.ningbaoqi_core.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * 请求码存储
 */
public class RequestCode {
    public static final int TAKE_PHOTO = 4;
    public static final int PICK_PHOTO = 5;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int TAKE_ERROR = UCrop.RESULT_ERROR;
    public static final int SCAN = 7;
}
