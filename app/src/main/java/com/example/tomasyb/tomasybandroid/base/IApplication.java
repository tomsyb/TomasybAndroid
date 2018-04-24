package com.example.tomasyb.tomasybandroid.base;

import android.app.Application;

import com.example.tomasyb.utilslib.utils.Utils;

/**
 * Created by Tomasyb on 2018-4-24.
 */

public class IApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
