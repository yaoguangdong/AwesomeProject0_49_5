package com.facebook.react.listeners;

/**
 * Description:
 * Created by yaoguangdong on 2017/12/29.
 */

public enum RNExceptionType {

    ERROR_TYPE_JS("js"),
    ERROR_TYPE_BUNDLE("bundle"),
    ERROR_TYPE_NATIVE("native"),
    ERROR_TYPE_OTHER("other");

    private final String mErrorType;

    RNExceptionType(String errorType) {
        mErrorType = errorType;
    }

    public String getErrorType() {
        return mErrorType;
    }

}
