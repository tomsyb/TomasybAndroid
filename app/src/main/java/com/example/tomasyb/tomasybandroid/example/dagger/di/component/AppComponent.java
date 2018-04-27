package com.example.tomasyb.tomasybandroid.example.dagger.di.component;

import com.example.tomasyb.tomasybandroid.example.dagger.di.module.ApiModule;
import com.example.tomasyb.tomasybandroid.example.dagger.entity.User;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Tomasyb on 2018-4-25.
 * 全局
 */
@Singleton
@Component(modules = ApiModule.class)
public interface AppComponent {
    OkHttpClient getClient();
    Retrofit getRetrofit();
    User getUser();
}
