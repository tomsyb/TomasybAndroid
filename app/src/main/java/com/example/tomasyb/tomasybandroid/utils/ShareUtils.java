package com.example.tomasyb.tomasybandroid.utils;

import yanb.sharelib.SocialHelper;

/**
 * 分享工具
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-20.10:54
 * @since JDK 1.8
 */

public enum ShareUtils {
    INSTANCE();

    public SocialHelper socialHelper;

    ShareUtils() {
        socialHelper = new SocialHelper.Builder()
                .setQqAppId("1106985215")
                .setWxAppId("wxAppId")
                .setWxAppSecret("wxAppSecret")
                .setWbAppId("wbAppId")
                .setWbRedirectUrl("wbRedirectUrl")
                .build();
    }
}
