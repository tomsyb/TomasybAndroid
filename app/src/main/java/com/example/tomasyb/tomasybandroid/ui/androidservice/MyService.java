package com.example.tomasyb.tomasybandroid.ui.androidservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.Time;

import com.example.tomasyb.baselib.util.LogUtils;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-3-4.10:22
 * @since JDK 1.8
 */

public class MyService extends Service{
    //这里定义吧一个Binder类，用在onBind()有方法里，这样Activity那边可以获取到
    private MyBinder myBinder = new MyBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.e("start IBinder~~~");
        return myBinder;
    }

    @Override
    public void onCreate() {
        LogUtils.e("start onCreate~~~");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        LogUtils.e("start onStart~~~");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        LogUtils.e("start onDestroy~~~");
        super.onDestroy();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.e("start onUnbind~~~");
        return super.onUnbind(intent);
    }
    //这里我写了一个获取当前时间的函数，不过没有格式化就先这么着吧
    public String getSystemTime(){
        Time t = new Time();
        t.setToNow();
        return t.toString();
    }
    public class MyBinder extends Binder {
        MyService getService()
        {
            return MyService.this;
        }
    }
}
