package com.example.tomasyb.tomasybandroid.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.ToastUtils;

/**
 * 实时的通过服务来控制界面
 * 服务：
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-11.17:57
 * @since JDK 1.8
 */

public class TextService extends Service {
    private String data = "服务器正在执行";

    public TextService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("执行onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e("执行onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //将其返回Activity
        LogUtils.e("执行onBind");
        return new Binder();
    }


    public class Binder extends android.os.Binder {
        public void sendData(String datas) {
            TextService.this.data = datas;
            ToastUtils.showLong("我得到Activity给我服务的数据是-->" + datas);
        }
    }

}
