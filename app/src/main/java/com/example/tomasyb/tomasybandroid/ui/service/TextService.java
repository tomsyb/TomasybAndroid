package com.example.tomasyb.tomasybandroid.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
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


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("执行onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e("执行onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //执行后台耗时操作
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("执行onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //将其返回Activity
        LogUtils.e("执行onBind");
        return binder;
    }

    /**
     * Service  与Activity进行通信
     */
    private MyBinder binder = new MyBinder();
    class MyBinder extends Binder{
        // 模拟后台进行下载任务
        public void startDownload(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //执行耗时操作
                }
            }).start();
            ToastUtils.showLong("开始下载");
        }

    }





}
