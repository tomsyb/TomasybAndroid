package com.example.tomasyb.baselib.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.tomasyb.baselib.base.IApp;
import com.example.tomasyb.baselib.di.component.AppComponent;

/**
 * Created by Tomasyb on 2018-4-27.
 */

public class AppDelegate implements IApp,IAppLifecycles{


    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return null;
    }

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {

    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
