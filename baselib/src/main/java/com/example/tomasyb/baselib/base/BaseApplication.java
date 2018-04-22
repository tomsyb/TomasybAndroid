package com.example.tomasyb.baselib.base;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.tomasyb.baselib.di.component.AppComponent;

/**
 * Created by Tomasyb on 2018-4-20.
 */

public class BaseApplication extends Application implements IApp{
    /**
     *
     * @return
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return null;
    }
}
