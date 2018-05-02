package com.example.tomasyb.tomasybandroid.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.utilslib.utils.L;

/**
 * Activity生命周期
 */
public class ActivityLifeActivity extends AppCompatActivity {

    /**
     * 创建 第一方法
     * Activity初始化工作
     * 1.加载布局
     * 2.变量初始化
     *
     * 处于后台不可见（动画不在这里因为不可见）
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        L.e("onCreate处于后台不可见");
    }

    /**
     * 处于可见，未出现在前台我们看不到
     */
    @Override
    protected void onStart() {
        super.onStart();
        L.e("onStart()处于可见，未出现在前台我们看不到");
    }

    /**
     * 前台，用户可见
     */
    @Override
    protected void onResume() {
        super.onResume();
        L.e("onResume()前台，用户可见");
    }

    /**
     * 暂停
     * Activity跳转或正常退出
     * 处于前台并可见
     * 可进行轻量级的存储数据和去初始化工作，不能太耗时（因为在跳Activity时只有当当前activity的onPause执行完才能
     * 另一个Activity才会启动，onPause在0.5秒没有执行完毕，会强制关闭Activity）
     */
    @Override
    protected void onPause() {
        super.onPause();
        L.e("onPause()暂停处于前台并可见");
    }

    /**
     * 停止
     * 不可见
     * Activity对象还在内存中没有被销毁
     * 做资源回收
     */
    @Override
    protected void onStop() {
        super.onStop();
        L.e("onStop()不可见");
    }

    /**
     * 销毁
     * Activity被销毁
     * 不可见
     * 释放资源，处理回收工作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e("onDestroy()销毁不可见");
    }

    /**
     * 重启
     * 可见
     * 触发机制：
     * 1.home键桌面和Activity切换
     * 2.ActivityB切回ActivityA
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
