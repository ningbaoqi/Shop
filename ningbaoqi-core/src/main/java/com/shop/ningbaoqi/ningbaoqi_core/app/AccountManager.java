package com.shop.ningbaoqi.ningbaoqi_core.app;

import com.shop.ningbaoqi.ningbaoqi_core.util.storage.NingbaoqiPreference;

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户登陆状态，登陆后调用
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        NingbaoqiPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return NingbaoqiPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }


    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
