package com.example.tomasyb.tomasybandroid.base;
import android.app.Application;

import com.example.tomasyb.baselib.util.LogUtils;

/**
 * Created by Tomasyb on 2018-4-24.
 */

public class IApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger
        LogUtils.logInit(true);
    }

}
