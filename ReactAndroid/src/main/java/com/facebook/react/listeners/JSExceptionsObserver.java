package com.facebook.react.listeners;

import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:监听js错误
 * Created by yaoguangdong on 2017/12/27.
 */

public class JSExceptionsObserver {

    private List<JSExceptionsListener> mListeners = new ArrayList<>();

    public void registerListener(JSExceptionsListener listener){
        if (listener != null) {
            mListeners.add(listener);
        }
    }

    public void unRegisterListener(JSExceptionsListener listener) {
        if (listener != null) {
            mListeners.remove(listener);
        }
    }

    /**
     * handle js fatal exception message
     * @param title
     * @param details
     * @param exceptionId
     */
    public void notifyExceptions(String title, ReadableArray details, int exceptionId){
        for (JSExceptionsListener listener : mListeners) {
            if (listener != null) {
                listener.reportFatalAndSoftException(title, details, exceptionId);
            }
        }
    }

    private JSExceptionsObserver(){
    }

    public static JSExceptionsObserver getInstance(){
        return JSExceptionsObserverHolder.sHolder;
    }

    private static class JSExceptionsObserverHolder {
        private static final JSExceptionsObserver sHolder = new JSExceptionsObserver();
    }

    /**
     * intercept js module's exception android transfer to listener
     */
    public interface JSExceptionsListener{

        /**
         * handle js soft exception message
         * @param title
         * @param details
         * @param exceptionId
         */
        void reportFatalAndSoftException(String title, ReadableArray details, int exceptionId);

    }

}
