package com.example.tomasyb.tomasybandroid.ui.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tomasyb.tomasybandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服务通信
 * 实现ServiceConnection重写
 * onServiceConnected 绑定成功
 * onServiceDisConnected 进程奔溃调用
 */
public class ServiceUseActivity extends AppCompatActivity implements ServiceConnection {
    //创建IBinder对象
    public TextService.Binder binder = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_use);
        ButterKnife.bind(this);
        intent = new Intent(ServiceUseActivity.this, TextService.class);
    }

    /**
     * 获取IBinder对象
     *
     * @param name
     * @param service
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (TextService.Binder) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @OnClick({R.id.btn_bindservice, R.id.btn_send,R.id.btn_closeservice,R.id.btn_intent_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bindservice://绑定服务
                startService(intent);
                break;
            case R.id.btn_send://ServiceConnection方式发送数据
                if (binder != null) {
                    binder.sendData("绑定服务");
                }
                break;
            case R.id.btn_closeservice:
                stopService(intent);
                break;
            case R.id.btn_intent_send:

                break;
        }
    }
}
