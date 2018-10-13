package com.example.tomasyb.tomasybandroid.ui.activitylifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.tomasyb.baselib.util.ToastUtils;

/**
 * 实时的通过服务来控制界面
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-11.17:57
 * @since JDK 1.8
 */

public class CountService extends Service{
    private String data  ="";

    public CountService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    public class Binder extends android.os.Binder{
        public void sendData(String datas){
            CountService.this.data = datas;
            ToastUtils.showLong("我得到Activity给我服务的数据是-->"+datas);
        }
    }

}
