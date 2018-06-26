package com.shop.ningbaoqi.ningbaoqi_core.delegates;

public abstract class NingbaoqiDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends NingbaoqiDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
