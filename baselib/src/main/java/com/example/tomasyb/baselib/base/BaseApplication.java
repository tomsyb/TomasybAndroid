package com.example.tomasyb.baselib.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.tomasyb.baselib.base.delegate.IAppLifecycles;
import com.example.tomasyb.baselib.di.component.AppComponent;

/**
 * Created by Tomasyb on 2018-4-20.
 */

public class BaseApplication extends Application implements IApp{
    private IAppLifecycles mAppDelegate;
    /**
     *
     * @return
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return null;
    }

    /**
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
