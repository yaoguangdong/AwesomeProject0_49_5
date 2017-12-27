/**
 * Copyright (c) 2017-present, Facebook, Inc.
 * All rights reserved.
 * <p>
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.testrn;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

public class MyJSStackTrace {

    public static String format(String message, ReadableArray stack) {
        StringBuilder stringBuilder = new StringBuilder(message).append(", stack:\n");
        for (int i = 0; i < stack.size(); i++) {
            ReadableMap frame = stack.getMap(i);
            stringBuilder.append(frame.getString("methodName")).append("@").append(stackFrameToModuleId(frame)).append(frame.getInt("lineNumber"));
            if (frame.hasKey("column") &&
                    !frame.isNull("column") &&
                    frame.getType("column") == ReadableType.Number) {
                stringBuilder.append(":").append(frame.getInt("column"));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    // If the file name of a stack frame is numeric (+ ".js"), we assume it's a lazily injected module
    // coming from a "random access bundle". We are using special source maps for these bundles, so
    // that we can symbolicate stack traces for multiple injected files with a single source map.
    // We have to include the module id in the stack for that, though. The ".js" suffix is kept to
    // avoid ambiguities between "module-id:line" and "line:column".
    private static String stackFrameToModuleId(ReadableMap frame) {
        if (frame.hasKey("file") &&
                !frame.isNull("file") &&
                frame.getType("file") == ReadableType.String) {
            return frame.getString("file");
        }
        return "";
    }
}