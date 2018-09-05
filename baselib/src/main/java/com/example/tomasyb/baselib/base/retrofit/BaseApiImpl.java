package com.example.tomasyb.baselib.base.retrofit;

import android.util.Log;

import com.example.tomasyb.baselib.net.interceptor.LoggingInterceptor;
import com.example.tomasyb.baselib.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*
 * 项目名:    BaseFrame
 * 包名       com.zhon.baselib.retroft
 * 文件名:    BaseApiImpl
 * 创建者:    严博
 * 创建时间:  2017/9/7 on 10:12
 * 描述:     TODO
 */
public class BaseApiImpl implements BaseApi {

    private volatile static Retrofit retrofit = null;
    protected Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

    public OkHttpClient.Builder getOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .readTimeout(20000, TimeUnit.MILLISECONDS)
                .connectTimeout(20000, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingInterceptor())
                //.addInterceptor(new HttpHeaderInterceptor())
                //.addNetworkInterceptor(new HttpCacheInterceptor())
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())  // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
                ;
    }


    public BaseApiImpl(String baseUrl) {
        OkHttpClient okHttpClient = getOkHttpClientBuilder().build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        retrofitBuilder
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }

    /**
     * 构建retroft
     *
     * @return Retrofit对象
     */
    @Override
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            //锁定代码块
            synchronized (BaseApiImpl.class) {
                if (retrofit == null) {
                    retrofit = retrofitBuilder.build(); //创建retrofit对象
                }
            }
        }
        return retrofit;

    }


    @Override
    public Retrofit.Builder setConverterFactory(Converter.Factory factory) {
        return retrofitBuilder.addConverterFactory(factory);
    }

}
