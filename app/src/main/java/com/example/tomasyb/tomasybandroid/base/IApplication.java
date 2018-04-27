package com.example.tomasyb.tomasybandroid.base;

import android.app.Application;

import com.example.tomasyb.tomasybandroid.example.dagger.di.component.AppComponent;
import com.example.tomasyb.tomasybandroid.example.dagger.di.component.DaggerAppComponent;
import com.example.tomasyb.tomasybandroid.example.dagger.di.module.ApiModule;
import com.example.tomasyb.utilslib.utils.Utils;

/**
 * Created by Tomasyb on 2018-4-24.
 */

public class IApplication extends Application{
    AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    //获取appComponent
    public AppComponent getAppComponent(){
        return appComponent;
    }
}
