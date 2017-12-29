package com.facebook.react.listeners;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:监听native错误
 * Created by yaoguangdong on 2017/12/27.
 */

public class RNExceptionsObserver {

    private List<RNExceptionsListener> mListeners = new ArrayList<>();

    public void registerListener(RNExceptionsListener listener){
        if (listener != null) {
            mListeners.add(listener);
        }
    }

    public void unRegisterListener(RNExceptionsListener listener) {
        if (listener != null) {
            mListeners.remove(listener);
        }
    }

    /**
     * handle native fatal exception message
     * @param errorType
     * @param e
     */
    public void notifyExceptions(RNExceptionType errorType, Exception e){
        for (RNExceptionsListener listener : mListeners) {
            if (listener != null) {
                listener.onException(errorType, e);
            }
        }
    }

    private RNExceptionsObserver(){
    }

    public static RNExceptionsObserver getInstance(){
        return JSExceptionsObserverHolder.sHolder;
    }

    private static class JSExceptionsObserverHolder {
        private static final RNExceptionsObserver sHolder = new RNExceptionsObserver();
    }

    /**
     * 监听native异常回调
     */
    public interface RNExceptionsListener {

        /**
         * handle native exception message
         * @param errorType
         * @param e
         */
        void onException(RNExceptionType errorType, Exception e);

    }

}
