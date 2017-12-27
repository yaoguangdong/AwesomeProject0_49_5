package com.testrn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.ExceptionsManagerHooker;
import com.facebook.react.shell.MainReactPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by yaoguangdong on 2017/12/27.
 */

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExceptionsManagerHooker.getInstance().registerListener(new ExceptionsManagerHooker.JSExceptionsListener() {
            @Override
            public void reportFatalAndSoftException(String title, ReadableArray readableArray, int exceptionId) {
                Log.v("ygd", MyJSStackTrace.format(title, readableArray));
            }
        });

        ReactInstanceManagerBuilder builder = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setCurrentActivity(this)
//                .setBundleAssetName("TestModuleFile_01.bundle")//正常文件
                .setBundleAssetName("TestModuleFile_02.bundle")//错误文件，用于捕捉js错误测试
                .addPackages(getReactPackages())
                .setUseDeveloperSupport(true)
                .setInitialLifecycleState(LifecycleState.RESUMED);
        ReactInstanceManager instanceManager = builder.build();

        if (instanceManager != null) {
            ReactRootView reactRootView = new ReactRootView(this);
            reactRootView.startReactApplication(instanceManager, "AwesomeProject", null);

            setContentView(reactRootView);
        }

    }

    private List<ReactPackage> getReactPackages() {
        List<ReactPackage> reactPackages = new ArrayList<>();
        reactPackages.add(new MainReactPackage());
        return reactPackages;
    }

}
