package com.example.tomasyb.baselib.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomasyb on 2018-4-20.
 * 提供框架一些必须的实例
 */
@Module
public abstract class AppModule {
    @Singleton
    @Provides
    static Gson provideGson(Application application, @Nullable GsonConfiguration configuration){
        GsonBuilder builder = new GsonBuilder();
        if (configuration!=null){
            configuration.configGson(application,builder);
        }
        return builder.create();
    }
    //Gson配置接口
    public interface GsonConfiguration{
        void configGson(Context context, GsonBuilder builder);
    }


}
