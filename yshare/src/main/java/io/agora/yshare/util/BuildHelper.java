/*
 * Copyright (c) 2015. BiliBili Inc.
 */

package io.agora.yshare.util;

import android.os.Build;

/**
 * @author Jungly
 * @email jungly.ik@gmail.com
 * @since 2016/4/11
 */
public class BuildHelper {
    public static int HONEYCOMB = 11;

    public static boolean isApi11_HoneyCombOrLater() {
        return getSDKVersion() >= HONEYCOMB;
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

}
