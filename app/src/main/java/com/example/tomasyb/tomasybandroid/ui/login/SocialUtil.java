package com.example.tomasyb.tomasybandroid.ui.login;


import io.agora.yshare.SocialHelper;

public enum SocialUtil {
    INSTANCE();

    public SocialHelper socialHelper;

    SocialUtil() {
        socialHelper = new SocialHelper.Builder()
                .setQqAppId("101533053")
                .setWxAppId("wx13ee55166072b68c")
                .setWxAppSecret("wxAppSecret")
                .setWbAppId("wbAppId")
                .setWbRedirectUrl("wbRedirectUrl")
                .build();
    }
}
