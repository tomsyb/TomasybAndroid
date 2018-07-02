package com.example.tomasyb.baselib.net;

import com.example.tomasyb.baselib.net.common.RetrofitUtils;

import retrofit2.Retrofit;

/**
 * Created by yanbo on 2018/7/2.
 * 通过RetrofitUtils获取ApiService实例
 */

public class Api {
    public static <T> T getApiService(Class<T> cls,String baseurl){
        Retrofit retrofit = RetrofitUtils.getRtBuilder(baseurl).build();
        return retrofit.create(cls);
    }
}
