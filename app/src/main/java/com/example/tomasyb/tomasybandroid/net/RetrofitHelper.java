package com.example.tomasyb.tomasybandroid.net;

import com.example.tomasyb.baselib.net.Api;
import com.example.tomasyb.tomasybandroid.common.Constant;

/**
 * Retrofit工具
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-3.14:49
 * @since JDK 1.8
 */

public class RetrofitHelper {
    private static ApiService mApiService;
    public static ApiService getmApiService(){
        return mApiService;
    }
    static {
        mApiService = Api.getApiService(ApiService.class, Constant.BASE_URL);
    }
}
