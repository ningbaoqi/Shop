package com.shop.ningbaoqi.ningbaoqi_shop.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.ningbaoqi.ningbaoqi_core.app.AccountManager;
import com.shop.ningbaoqi.ningbaoqi_shop.database.DatabaseManager;
import com.shop.ningbaoqi.ningbaoqi_shop.database.UserProfile;

public class SignHandler {
    public static void onSignUp(String response, ISignListener listener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);//插入数据到数据库

        /**
         * 已经注册成功
         */
        AccountManager.setSignState(true);
        listener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener listener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);//插入数据到数据库

        /**
         * 已经注册成功
         */
        AccountManager.setSignState(true);
        listener.onSignInSuccess();
    }
}
