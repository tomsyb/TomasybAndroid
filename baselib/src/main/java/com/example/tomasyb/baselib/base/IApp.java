package com.example.tomasyb.baselib.base;

import android.support.annotation.NonNull;

import com.example.tomasyb.baselib.di.component.AppComponent;

/**
 * Created by Tomasyb on 2018-4-20.
 * Application 必须实现此
 */

public interface IApp {
    @NonNull
    AppComponent getAppComponent();
}
