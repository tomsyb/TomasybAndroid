package com.example.tomasyb.tomasybandroid.ui.service;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tomasyb.baselib.rx.permission.RxPermissions;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 服务通信
 * 实现ServiceConnection重写
 * onServiceConnected 绑定成功
 * onServiceDisConnected 进程奔溃调用
 */
public class ServiceUseActivity extends AppCompatActivity implements ServiceConnection {
    private TextService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_use);
        ButterKnife.bind(this);
    }

    /**
     * 获取IBinder对象
     *
     * @param name
     * @param service
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        myBinder = (TextService.MyBinder) service;
        myBinder.startDownload();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        // 成功
                        if (aBoolean){

                        }
                    }
                });

    }

    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.btn_bind, R.id.btn_unbind,R.id.btn_start_fond,R.id.btn_stop_fond})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start://绑定服务
                /**
                 * 不管执行多少次onCreate执行一次
                 */
                Intent startIntent = new Intent(this, TextService.class);
                startService(startIntent);
                break;
            case R.id.btn_stop://取消绑定
                Intent stopIntent = new Intent(this, TextService.class);
                stopService(stopIntent);
                break;
            case R.id.btn_bind:
                Intent bindItent = new Intent(this,TextService.class);
                //第3个参数 :service与Activity建立关系后自动创建Service使onCreate方法执行，而onStartCommand方法不会执行
                bindService(bindItent,this,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(this);
                break;
            case R.id.btn_start_fond:
                Intent frontIntent = new Intent(this, FrontService.class);
                startService(frontIntent);
                break;
            case R.id.btn_stop_fond:
                Intent frontIntentstop = new Intent(this, FrontService.class);
                stopService(frontIntentstop);
                break;
        }
    }
}
