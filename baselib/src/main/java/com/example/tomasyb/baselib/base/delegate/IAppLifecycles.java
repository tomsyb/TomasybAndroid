package com.example.tomasyb.baselib.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Tomasyb on 2018-4-27.
 * application的生命周期代理
 */

public interface IAppLifecycles {
    void attachBaseContext(@NonNull Context base);
    void onCreate(@NonNull Application application);
    void onTerminate(@NonNull Application application);
}
