package com.example.tomasyb.tomasybandroid.api;

import com.example.tomasyb.baselib.base.api.Api;

/**
 * 外部网络请求
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-8.15:12
 * @since JDK 1.8
 */

public class AppApi {
    public static AppService getService(){
        return Api.getDefault("http://c.m.163.com/").create(AppService.class);
    }
}
