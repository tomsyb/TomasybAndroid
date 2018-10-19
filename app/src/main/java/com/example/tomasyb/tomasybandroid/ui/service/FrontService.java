package com.example.tomasyb.tomasybandroid.ui.service;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.tomasyb.tomasybandroid.R;

/**
 * 前台Service，一直存在的Service
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-19.14:20
 * @since JDK 1.8
 */

public class FrontService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Notification.Builder builder = new Notification.Builder(this);//新建Notification.Builder对象
        Intent notificationIntent = new Intent(this, ServiceUseActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentTitle("This is title");//设置标题
        builder.setContentText("This is content");
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置图片
        builder.setContentIntent(pendingIntent);//执行intent
        Notification notification = builder.getNotification();
        startForeground(1, notification);//让 MyService

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
