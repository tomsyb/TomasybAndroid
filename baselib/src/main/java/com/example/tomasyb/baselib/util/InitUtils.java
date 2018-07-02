package com.example.tomasyb.baselib.util;

import android.content.Context;

/**
 * 初始化工具类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-2.17:19
 * @since JDK 1.8
 */

public class InitUtils {
    private static Context context;
    private InitUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        InitUtils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
}
