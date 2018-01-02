/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.react.bridge;

import com.facebook.react.listeners.RNExceptionType;
import com.facebook.react.listeners.RNExceptionsObserver;

/**
 * Crashy crashy exception handler.
 */
public class DefaultNativeModuleCallExceptionHandler implements NativeModuleCallExceptionHandler {

  @Override
  public void handleException(Exception e) {
    RNExceptionsObserver.getInstance().notifyExceptions(RNExceptionType.ERROR_TYPE_NATIVE, e);
    if (e instanceof RuntimeException) {
      // Because we are rethrowing the original exception, the original stacktrace will be
      // preserved.
      throw (RuntimeException) e;
    } else {
      throw new RuntimeException(e);
    }
  }
}
