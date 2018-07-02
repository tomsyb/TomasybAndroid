package com.example.tomasyb.baselib.net.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.tomasyb.baselib.base.BaseConfig;
import com.example.tomasyb.baselib.net.converter.GsonConverterFactory;
import com.example.tomasyb.baselib.net.interceptor.HttpCacheInterceptor;
import com.example.tomasyb.baselib.net.interceptor.HttpHeaderInterceptor;
import com.example.tomasyb.baselib.net.interceptor.LoggingInterceptor;
import com.example.tomasyb.baselib.util.InitUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


/**
 * Retrofit工具类
 * 构建Retrofit.Builder，并对Okhttp配置
 * 1、 设置日志拦截器，拦截服务器返回的json数据。Retrofit将请求到json数据直接转换成了实体类，
 * 但有时候我们需要查看json数据，Retrofit并没有提供直接获取json数据的功能。
 * 因此我们需要自定义一个日志拦截器拦截json数据，并输入到控制台。
 *
 * 2. 设置Http请求头。给OkHttp 添加请求头拦截器，配置请求头信息。还可以为接口统一添加请求头数据。
 * 例如，把用户名、密码（或者token）统一添加到请求头。后续每个接口的请求头中都会携带用户名、
 * 密码（或者token）数据，避免了为每个接口单独添加。
 *
 * 3. 为OkHttp配置缓存。同样可以同过拦截器实现缓存处理。包括控制缓存的最大生命值，控制缓存的过期时间。
 *
 * 4. 如果采用https，我们还可以在此处理证书校验以及服务器校验。
 *
 * 5. 为Retrofit添加GsonConverterFactory
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-2.17:17
 * @since JDK 1.8
 */

public class RetrofitUtils {
    /**
     *
     * @return 获取OkHttpClient.Builder
     */
    public static OkHttpClient.Builder getOkBuilder(){
        //缓存文件
        File cacheFile = new File(InitUtils.getContext().getCacheDir(),"cache");
        Cache cache= new Cache(cacheFile,1024*1024*100);//100Mb
        return new OkHttpClient.Builder()
                .readTimeout(BaseConfig.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(BaseConfig.CONNECT_TIME_OUT,TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HttpHeaderInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())  // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache);
    }

    /**
     *
     * @param baseUrl 根地址
     * @return 获取Retrofit.Builder
     */
    public static Retrofit.Builder getRtBuilder(String baseUrl){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        OkHttpClient client = RetrofitUtils.getOkBuilder().build();
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }

}
