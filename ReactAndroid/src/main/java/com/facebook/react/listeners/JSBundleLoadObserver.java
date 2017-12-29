package com.facebook.react.listeners;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by yaoguangdong on 2017/12/28.
 */

public class JSBundleLoadObserver {

    private List<JSBundleLoadListener> mListeners = new ArrayList<>();

    public void registerListener(JSBundleLoadListener listener){
        if (listener != null) {
            mListeners.add(listener);
        }
    }

    public void unRegisterListener(JSBundleLoadListener listener) {
        if (listener != null) {
            mListeners.remove(listener);
        }
    }

    /**
     * 从Assets中加载加载js文件
     * @param resultCode
     * @param assetUrl
     * @param loadSynchronously
     */
    public void loadScriptFromAssets(int resultCode, String assetUrl, boolean loadSynchronously){
        for (JSBundleLoadListener listener : mListeners) {
            if (listener != null) {
                listener.loadScriptFromAssets(resultCode, assetUrl, loadSynchronously);
            }
        }
    }

    /**
     * 从本地文件系统中加载js文件
     * @param resultCode
     * @param fileName
     * @param loadSynchronously
     */
    public void loadScriptFromFile(int resultCode, String fileName, boolean loadSynchronously){
        for (JSBundleLoadListener listener : mListeners) {
            if (listener != null) {
                listener.loadScriptFromFile(resultCode, fileName, loadSynchronously);
            }
        }
    }

    /**
     * 从网络加载
     * @param resultCode
     * @param sourceURL
     */
    public void loadScriptFromNetworkLoader(int resultCode, String sourceURL){
        for (JSBundleLoadListener listener : mListeners) {
            if (listener != null) {
                listener.loadScriptFromNetworkLoader(resultCode, sourceURL);
            }
        }
    }

    /**
     * 从debug 代理环境网络加载
     * @param resultCode
     * @param realSourceURL
     */
    public void loadScriptFromRemoteDebugger(int resultCode, String realSourceURL){
        for (JSBundleLoadListener listener : mListeners) {
            if (listener != null) {
                listener.loadScriptFromRemoteDebugger(resultCode, realSourceURL);
            }
        }
    }

    private JSBundleLoadObserver(){
    }

    public static JSBundleLoadObserver getInstance(){
        return JSBundleLoadObserver.JSBundleLoadListenerHolder.sHolder;
    }

    private static class JSBundleLoadListenerHolder {
        private static final JSBundleLoadObserver sHolder = new JSBundleLoadObserver();
    }

    /**
     * listen js bundle file load
     */
    public interface JSBundleLoadListener{

        /**
         * 从Assets中加载加载js文件
         * @param resultCode
         * @param assetUrl
         * @param loadSynchronously
         */
        void loadScriptFromAssets(int resultCode, String assetUrl, boolean loadSynchronously);

        /**
         * 从本地文件系统中加载js文件
         * @param resultCode
         * @param fileName
         * @param loadSynchronously
         */
        void loadScriptFromFile(int resultCode, String fileName, boolean loadSynchronously);

        /**
         * 从网络加载
         * @param resultCode
         * @param sourceURL
         */
        void loadScriptFromNetworkLoader(int resultCode, String sourceURL);

        /**
         * 从debug 代理环境网络加载
         * @param resultCode
         * @param realSourceURL
         */
        void loadScriptFromRemoteDebugger(int resultCode, String realSourceURL);

    }

}
