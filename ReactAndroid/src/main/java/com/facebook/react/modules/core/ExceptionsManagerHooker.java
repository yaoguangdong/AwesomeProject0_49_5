package com.facebook.react.modules.core;

import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by yaoguangdong on 2017/12/27.
 */

public class ExceptionsManagerHooker {

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

    private ExceptionsManagerHooker(){
    }

    public static ExceptionsManagerHooker getInstance(){
        return ExceptionsManagerHookerHolder.sHooker;
    }

    private static class ExceptionsManagerHookerHolder {
        private static final ExceptionsManagerHooker sHooker = new ExceptionsManagerHooker();
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
