package com.shop.ningbaoqi.ningbaoqi_core.delegates;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.shop.ningbaoqi.ningbaoqi_core.ui.camera.CameraImageBean;
import com.shop.ningbaoqi.ningbaoqi_core.ui.camera.NingbaoqiCamera;
import com.shop.ningbaoqi.ningbaoqi_core.ui.camera.RequestCodes;
import com.shop.ningbaoqi.ningbaoqi_core.util.callback.CallBackManager;
import com.shop.ningbaoqi.ningbaoqi_core.util.callback.CallbackType;
import com.shop.ningbaoqi.ningbaoqi_core.util.callback.IGlobalCallback;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    //不是直接调用的方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        NingbaoqiCamera.start(this);
    }


    //这个是真正调用的方法
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }


    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
    }


    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext()).setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.proceed();
            }
        }).setNegativeButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).setCancelable(false).setMessage("权限管理").show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        Toast.makeText(getContext(), " 永久拒绝权限 ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("nbq", (resultCode == RESULT_OK) + "--------" + requestCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    if (getContext() != null) {
                        UCrop.of(resultUri, resultUri)//剪裁后的赋值给原来的
                                .withMaxResultSize(400, 400).start(getContext(), this);
                    }
                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        final String pickCropPath = NingbaoqiCamera.createCropFile().getPath();//从相册选择后需要有个路径存放剪裁过的图片
                        if (pickPath != null && getContext() != null) {
                            UCrop.of(pickPath, Uri.parse(pickCropPath)).withMaxResultSize(400, 400).start(getContext(), this);
                        }
                    }
                    break;
                case RequestCodes.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的数据进行处理
                    final IGlobalCallback<Uri> callback = CallBackManager.getInstance().getCallback(CallbackType.ON_CROP);
                    if (callback != null) {
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }
}
