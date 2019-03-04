package com.example.tomasyb.tomasybandroid.ui.androidservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 启动方式：
 * 1.startService
 * startService() --> onCreate()--> onStartConmon()--> onDestroy()
 * 当我们通过startService被调用以后，多次在调用startService(),onCreate()方法也只会被调用一次，而onStartConmon()会被多次调用当我们调用stopService()的时候，onDestroy()就会被调用，从而销毁服务
 * 当我们通过startService启动时候，通过intent传值，在onStartConmon()方法中获取值的时候，一定要先判断intent是否为null
 * 2.bindService
 * bindService-->onCreate()-->onBind()-->unBind()-->onDestroy()
 * 更加便利activity中操作service，比如加入service中有几个方法，a,b ，如果要在activity中调用，在需要在activity获取ServiceConnection对象，通过ServiceConnection来获取service中内部类的类对象，然后通过这个类对象就可以调用类中的方法，当然这个类需要继承Binder对象
 *
 */
public class ServiceMainActivity extends AppCompatActivity {
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.btn_startservice)
    Button btnStartservice;
    @BindView(R.id.btn_stopservice)
    Button btnStopservice;
    @BindView(R.id.btn_bindservice)
    Button btnBindservice;
    @BindView(R.id.btn_unbindservice)
    Button btnUnbindservice;
    private MyService myService;
    //这里需要用到ServiceConnection在Context.bindService和context.unBindService()里用到
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myService = ((MyService.MyBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_startservice, R.id.btn_stopservice, R.id.btn_bindservice, R.id
            .btn_unbindservice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startservice:
                Intent intent = new Intent();
                intent.setClass(this,MyService.class);
                startService(intent);
                tvShow.setText("执行onCreate--onStart");
                break;
            case R.id.btn_stopservice:
                Intent intent2 = new Intent();
                intent2.setClass(this,MyService.class);
                stopService(intent2);
                tvShow.setText("执行onDestroy");
                break;
            case R.id.btn_bindservice:
                Intent intent3 = new Intent();
                intent3.setClass(this,MyService.class);
                bindService(intent3,connection,BIND_AUTO_CREATE);
                tvShow.setText("执行onCreate--IBinder");
                break;
            case R.id.btn_unbindservice:
                unbindService(connection);
                tvShow.setText("执行onUnbind--onDestroy");
                break;
        }
    }
}
