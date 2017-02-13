package com.github.raininfall.ys;

import android.app.Application;

import com.videogo.openapi.EZOpenSDK;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class YSModule extends ReactContextBaseJavaModule implements LifecycleEventListener{


    final ReactApplicationContext mContext;

    public YSModule(ReactApplicationContext reactContext) {
        super(reactContext);

        mContext = reactContext;
        mContext.addLifecycleEventListener(this);
    }

    @Override
    public String getName() {
        return "YS";
    }

    @ReactMethod
    public void showSDKLog(boolean bShowLog) {
      EZOpenSDK.showSDKLog(bShowLog);
    }

    @ReactMethod
    public void enableP2P(boolean bEnable){
      EZOpenSDK.enableP2P(true);
    }

    @ReactMethod
    public void initLib(String appKey, String loadLibraryAbsPath, Callback callback) {
      callback.invoke(EZOpenSDK.initLib((Application)mContext.getApplicationContext(), appKey, loadLibraryAbsPath));
    }

    @ReactMethod
    public void setAccessToken(String accessToken) {
        EZOpenSDK.getInstance().setAccessToken(accessToken);
    }

    @Override
    public void onHostResume() {
        //TODO:
    }
    @Override
    public void onHostPause() {
        //TODO:
    }

    @Override
    public void onHostDestroy() {
        //TODO:
    }
  }
