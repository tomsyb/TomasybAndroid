package com.example.tomasyb.tomasybandroid.ui.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tomasyb.baselib.util.NotifyUtil;
import com.example.tomasyb.tomasybandroid.MainActivity;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 通知包括android8.0
 */
public class NotificationActivity extends AppCompatActivity {
    private Button btn_send_chat;
    private Button btn_send_subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 重要等级还可以设置为IMPORTANCE_LOW、IMPORTANCE_MIN
     */
    private void initView() {
        btn_send_chat = findViewById(R.id.btn_send_chat);
        btn_send_subscribe = findViewById(R.id.btn_send_scribe);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//表示在26以上执行下面操作
            String channelId = "chat";// 渠道id（随便定义只要保证全局唯一性即可）
            String channelName = "聊天消息";//渠道名称(给用户看的)
            int importance = NotificationManager.IMPORTANCE_HIGH;// 重要等级很高
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;//这类消息不怎么重要
            createNotificationChannel(channelId, channelName, importance);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @OnClick({R.id.btn_send_chat, R.id.btn_send_scribe,R.id.btn_single})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_chat:
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification  = new NotificationCompat.Builder(this, "chat")
                        .setContentTitle("收到一条聊天消息")
                        .setContentText("今天中午吃什么?")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.icon_logo)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap
                                .icon_logo))
                        .setAutoCancel(true)
                        .build();
                manager.notify(1,notification );
                break;
            case R.id.btn_send_scribe:
                NotificationManager manager1 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification1 = new NotificationCompat.Builder(this, "subscribe")
                        .setContentTitle("收到一条订阅消息")
                        .setContentText("地铁沿线30万商铺抢购中！")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.icon_logo)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_logo))
                        .setAutoCancel(true)
                        .build();
                manager1.notify(2, notification1);
                break;
            case R.id.btn_single:
                //设置想要展示的数据内容
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pIntent = PendingIntent.getActivity(this,
                        54, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                int smallIcon = R.drawable.ic_qq;
                String ticker = "您有一条新通知";
                String title = "双十一大优惠！！！";
                String content = "仿真皮肤充气娃娃，女朋友带回家！";

                //实例化工具类，并且调用接口
                NotifyUtil notify1 = new NotifyUtil(this, 1);
                notify1.notify_normal_singline(pIntent, smallIcon, ticker, title, content, true, true, false);
                break;
        }
    }
}
