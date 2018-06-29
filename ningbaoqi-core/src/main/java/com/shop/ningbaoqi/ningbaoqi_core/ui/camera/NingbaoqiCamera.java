package com.shop.ningbaoqi.ningbaoqi_core.ui.camera;

import android.net.Uri;

import com.shop.ningbaoqi.ningbaoqi_core.delegates.PermissionCheckerDelegate;
import com.shop.ningbaoqi.ningbaoqi_core.util.file.FileUtil;

/**
 * 相机调用类
 */
public class NingbaoqiCamera {
    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image", FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
