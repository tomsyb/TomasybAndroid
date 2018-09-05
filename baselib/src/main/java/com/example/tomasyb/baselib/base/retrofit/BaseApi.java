package com.example.tomasyb.baselib.base.retrofit;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/*
 * 项目名:    Pigeon
 * 包名      com.zhon.baselib.mvpbase
 * 文件名:    BaseApi
 * 创建者:    严博
 * 创建时间:  2017/5/3 on 14:08
 * 描述:     TODO
 */
public interface BaseApi {
    Retrofit getRetrofit();
    Retrofit.Builder setConverterFactory(Converter.Factory factory);

}
