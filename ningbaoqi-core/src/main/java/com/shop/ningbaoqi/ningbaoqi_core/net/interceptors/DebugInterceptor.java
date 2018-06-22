package com.shop.ningbaoqi.ningbaoqi_core.net.interceptors;

import android.support.annotation.RawRes;
import android.util.Log;

import com.shop.ningbaoqi.ningbaoqi_core.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int DEBUG_ROW_ID;

    public DebugInterceptor(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_ROW_ID = debugRawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawID) {
        final String json = FileUtil.getRawFile(rawID);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        Log.d("nbq", url + "------" + DEBUG_URL);
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_ROW_ID);
        }
        return chain.proceed(chain.request());//原样返回
    }
}
