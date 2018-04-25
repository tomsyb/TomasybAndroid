package com.example.tomasyb.tomasybandroid.example.dagger.di.module;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Tomasyb on 2018-4-25.
 * 网络请求，实现全局
 *  @Singleton 全局单列
 */
@Module
public class ApiModule {
    public static final String END_POINT = "http://www.baidu.com";
    @Provides
    @Singleton
   OkHttpClient provideOkhttpClient(){
       OkHttpClient client = new OkHttpClient.Builder()
               .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
               .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
               .build();
       return client;
   }

    /**
     *
     * @param client 由本类上面提供
     * @return
     */
   @Provides
   @Singleton
   Retrofit provideRetrofit(OkHttpClient client){
       Retrofit retrofit = new Retrofit.Builder()
               .client(client)
               .baseUrl(END_POINT)
               .build();
       return retrofit;
   }

}
